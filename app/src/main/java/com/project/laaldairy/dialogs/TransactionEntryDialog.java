package com.project.laaldairy.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import com.project.laaldairy.R;
import com.project.laaldairy.entity.Transaction;
import com.project.laaldairy.fragments.TimeLineFragment;
import com.project.laaldairy.util.DateFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TransactionEntryDialog {

    private Dialog dialog;
    private String[] data = {"Default", "Rent"};

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void show(Activity activity, TimeLineFragment timeLineFragment) {

        final StringBuilder[] stringDateAndTime = {new StringBuilder(), new StringBuilder()};

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.transaction_entry_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        Calendar calendar = Calendar.getInstance();

        Spinner dayPicker = dialog.findViewById(R.id.transactionDay);
        Spinner categoryPicker = dialog.findViewById(R.id.categoryPicker);

        EditText title = dialog.findViewById(R.id.transactionTitle);
        EditText amount = dialog.findViewById(R.id.transactionAmount);
        EditText date = dialog.findViewById(R.id.transactionDate);
        EditText time = dialog.findViewById(R.id.transactionTime);

        RadioGroup radioGroupTransaction = dialog.findViewById(R.id.radioGroupTransaction);
        final boolean[] credit = {true};

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, null, year, month, day);
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                stringDateAndTime[1].setLength(0);
                stringDateAndTime[1].append((hourOfDay < 10 ? "0" + hourOfDay : hourOfDay) + ":" + (minute < 10 ? "0" + minute : minute));
                time.setText(stringDateAndTime[1].toString());
            }
        }, hour, minute, true);

        stringDateAndTime[0].append((day < 10 ? "0" + day : day) + "/" + ((month < 10) ? "0" + month : month) + "/" + year);
        stringDateAndTime[1].append((hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute));


        date.setText(stringDateAndTime[0]);
        time.setText(stringDateAndTime[1]);


        datePickerDialog.setOnDateSetListener((view, selectedYear, selectedMonth, selectedDay) -> {
            stringDateAndTime[0].setLength(0);
            stringDateAndTime[0].append((selectedDay < 10 ? "0" + selectedDay : selectedDay) + "/" + ((selectedMonth < 10) ? "0" + selectedMonth : selectedMonth) + "/" + selectedYear);
            date.setText(stringDateAndTime[0].toString());
        });


        radioGroupTransaction.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.transactionCredit) credit[0] = true;
            if (checkedId == R.id.transactionDebit) credit[0] = false;
        });

        time.setOnClickListener(event -> {
            timePickerDialog.show();
        });

        dialog.findViewById(R.id.save).setOnClickListener(event -> {
            Transaction transaction = new Transaction();

            if (title.getText().toString().isEmpty()) {
                title.setError("Title is empty");
                return;
            }
            if (amount.getText().toString().isEmpty()) {
                amount.setError("Amount is mandatory");
                return;
            }

            transaction.setTitle(title.getText().toString());
            transaction.setCategory(categoryPicker.getSelectedItem().toString());
            transaction.setDate(stringDateAndTime[0].toString());
            transaction.setTime(stringDateAndTime[1].append(":00").toString());

            double amountInDouble = Double.parseDouble(amount.getText().toString());
            if (!credit[0]) amountInDouble = (-1) * amountInDouble;

            transaction.setAmount(amountInDouble);
            timeLineFragment.saveTransaction(transaction);
            dialog.dismiss();
        });

        dialog.findViewById(R.id.dimiss).setOnClickListener(event -> {
            dialog.dismiss();
        });

        date.setOnClickListener(event -> {
            datePickerDialog.show();
        });

        List<String> categories = new ArrayList<String>();
        categories.add("Category");
        categories.add("Rent");

        List<String> days = Arrays.asList(DateFormatter.getDays());//Dummy data

        dayPicker.setPrompt(days.get(calendar.get(Calendar.DAY_OF_WEEK)));
        dayPicker.setAdapter(getAdapter(activity, days));
        dayPicker.setSelection(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        categoryPicker.setAdapter(getAdapter(activity, categories));

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.85);

        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }


    private static ArrayAdapter<String> getAdapter(Activity activity, List<String> categories) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
