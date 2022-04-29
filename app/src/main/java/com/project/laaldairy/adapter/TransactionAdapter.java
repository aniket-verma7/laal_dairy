package com.project.laaldairy.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.laaldairy.R;
import com.project.laaldairy.entity.Transaction;
import com.project.laaldairy.util.DateFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.Holder> {

    private Map<String, List<Transaction>> transactionMap;
    private List<String> dates;

    public TransactionAdapter(Map<String, List<Transaction>> transactionMap) {
        this.transactionMap = transactionMap;
        dates = new ArrayList<String>(this.transactionMap.keySet());
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setAdapter(position);
        try {
            holder.transactionDate.setText(DateFormatter.getFormattedDate(dates.get(position)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return transactionMap.size();
    }

    public void updateAdapter() {
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView transactionDate;
        ImageView transactionShow;
        RecyclerView rcvSubTransactions;
        LinearLayout transactionLayout;
        SubTransactionAdapter adapter;
        boolean clicked;

        public Holder(@NonNull View itemView) {
            super(itemView);
            transactionDate = itemView.findViewById(R.id.transaction_date);
            transactionShow = itemView.findViewById(R.id.transaction_show);
            rcvSubTransactions = itemView.findViewById(R.id.transactions);
            rcvSubTransactions.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.VERTICAL,false));
            transactionLayout = itemView.findViewById(R.id.transaction_layout);
            clicked = false;
//
//            //TODO : add animation in the expand and collapse
            transactionShow.setOnClickListener(event -> {
                if (!clicked) {
                    clicked = true;
                    transactionShow.setImageResource(R.drawable.collapse);
                    transactionLayout.setVisibility(View.VISIBLE);
                } else {
                    clicked = false;
                    transactionShow.setImageResource(R.drawable.expand);
                    transactionLayout.setVisibility(View.GONE);
                }
            });
        }

        public void setAdapter(int position)
        {
            if(adapter == null){
                adapter = new SubTransactionAdapter(transactionMap.get(dates.get(position)));
                rcvSubTransactions.setAdapter(adapter);
            }
        }
    }
}
