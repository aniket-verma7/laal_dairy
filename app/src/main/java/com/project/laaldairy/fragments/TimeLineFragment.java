package com.project.laaldairy.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.project.laaldairy.R;
import com.project.laaldairy.dao.TransactionDao;
import com.project.laaldairy.database.TransactionDatabase;
import com.project.laaldairy.entity.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TimeLineFragment extends Fragment implements View.OnClickListener {

    private TabLayout tabLayout;
    private FragmentManager supportManager;
    private FloatingActionButton addTransaction;
    private List<Transaction> allTransaction;
    private Map<Integer,List<Transaction>> transactionMap;

    public TimeLineFragment(FragmentManager getSupportFragmentManager) {
        this.supportManager = getSupportFragmentManager;
        transactionMap = new HashMap<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);
        getAllTransaction();
        tabLayout = view.findViewById(R.id.statusTab);
        tabLayout.addOnTabSelectedListener(mListener);

        supportManager.beginTransaction().replace(R.id.transactionStatus, new CreditFragment(allTransaction)).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        addTransaction = view.findViewById(R.id.addTransaction);
        addTransaction.setColorFilter(Color.RED);
        addTransaction.setOnClickListener(this);

        return view;
    }

    private TabLayout.OnTabSelectedListener mListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    supportManager.beginTransaction().replace(R.id.transactionStatus, new CreditFragment(transactionMap.get(0))).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case 1:
                    supportManager.beginTransaction().replace(R.id.transactionStatus, new DebitFragment(transactionMap.get(1))).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case 2:
                    supportManager.beginTransaction().replace(R.id.transactionStatus, new AllFragment(transactionMap.get(2))).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}

        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//        TransactionDao dao = TransactionDatabase.getInstance(getContext()).getTransactionDao();
//        dao.insert(new Transaction("Dummy title 1","Dummy Category 1",new Date().toString(),100));
//        dao.insert(new Transaction("Dummy title 2","Dummy Category 2",new Date().toString(),-100.99));
//        dao.insert(new Transaction("Dummy title 3","Dummy Category 3",new Date().toString(),50));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getAllTransaction()
    {
        allTransaction = TransactionDatabase.getInstance(getContext()).getTransactionDao().getAllTransaction();
        Predicate<Transaction> creditTransaction = transaction -> transaction.getAmount()>=0;
        Predicate<Transaction> debitTransaction = transaction -> transaction.getAmount()<0;

        transactionMap.put(0,allTransaction.stream().filter(creditTransaction).collect(Collectors.toList()));
        transactionMap.put(1,allTransaction.stream().filter(debitTransaction).collect(Collectors.toList()));
        transactionMap.put(2,allTransaction);
    }
}
