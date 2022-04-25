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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.project.laaldairy.R;
import com.project.laaldairy.dao.TransactionDao;
import com.project.laaldairy.database.TransactionDatabase;
import com.project.laaldairy.dialogs.TransactionEntryDialog;
import com.project.laaldairy.entity.Transaction;
import com.project.laaldairy.view_model.TransactionViewModel;

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
    private TransactionViewModel transactionViewModel;
    private Map<Integer,List<Transaction>> transactionMap;
    private final Predicate<Transaction> creditTransaction = transaction -> transaction.getAmount() >= 0;
    private final Predicate<Transaction> debitTransaction = transaction -> transaction.getAmount() < 0;
    private CreditFragment creditFragment;
    private DebitFragment debitFragment;
    private AllFragment allFragment;

    public TimeLineFragment(FragmentManager getSupportFragmentManager) {
        this.supportManager = getSupportFragmentManager;
        transactionMap = new HashMap<>();
        allTransaction = new ArrayList<>();
        creditFragment = new CreditFragment(allTransaction);
        debitFragment = new DebitFragment(allTransaction);
        allFragment = new AllFragment(allTransaction);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        transactionMap = new HashMap<>();
        applyObserver();


        tabLayout = view.findViewById(R.id.statusTab);
        tabLayout.addOnTabSelectedListener(mListener);

        creditFragment.setCreditTransactionList(transactionMap.get(0));
        supportManager.beginTransaction().replace(R.id.transactionStatus,creditFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        addTransaction = view.findViewById(R.id.addTransaction);
        addTransaction.setColorFilter(Color.RED);
        addTransaction.setOnClickListener(this);

        return view;
    }

    //TODO : change the filtering logic;apply filtering in ViewModel class
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void applyObserver() {
        transactionViewModel.getTransaction().observe(getViewLifecycleOwner(), new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                allTransaction = transactions;
                System.out.println(allTransaction.toString());
                filterTransactionList();
            }
        });
        allTransaction = transactionViewModel.getTransaction().getValue();
        filterTransactionList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterTransactionList()
    {
        transactionMap.put(0, allTransaction.stream().filter(creditTransaction).collect(Collectors.toList()));
        transactionMap.put(1, allTransaction.stream().filter(debitTransaction).collect(Collectors.toList()));
        transactionMap.put(2, allTransaction);
    }

    private TabLayout.OnTabSelectedListener mListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    creditFragment.setCreditTransactionList(transactionMap.get(0));
                    supportManager.beginTransaction().replace(R.id.transactionStatus,creditFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case 1:
                    debitFragment.setDebitTransactionList(transactionMap.get(1));
                    System.out.println(transactionMap.get(1).toString());
                    supportManager.beginTransaction().replace(R.id.transactionStatus, debitFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case 2:
                    allFragment.setAllTransactionList(transactionMap.get(2));
                    supportManager.beginTransaction().replace(R.id.transactionStatus,allFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}

        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        TransactionEntryDialog dialog = new TransactionEntryDialog();
        dialog.show(getActivity(),this);
    }


    /**
     * save transaction entity object to ROOM.
     * @param transaction : Transaction entity object.
     */
    public void saveTransaction(Transaction transaction) {
        transactionViewModel.addTransaction(transaction);
        Toast.makeText(getActivity(), "Transaction Saved", Toast.LENGTH_SHORT).show();
    }
}
