package com.project.laaldairy.fragments;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.laaldairy.R;
import com.project.laaldairy.adapter.TransactionAdapter;
import com.project.laaldairy.entity.Transaction;
import com.project.laaldairy.home.MainActivity;
import com.project.laaldairy.util.GroupTransactions;

import java.util.List;
import java.util.Map;

public class DebitFragment extends Fragment {

    private RecyclerView rcvTransaction;
    private TransactionAdapter adapter;
    private Map<String,List<Transaction>> transactionByDateMap;
    private List<Transaction> debitTransactionList;

    public DebitFragment(List<Transaction> debitTransactionList) {
        this.debitTransactionList = debitTransactionList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.debit_fragment_ui,container,false);
        rcvTransaction = v.findViewById(R.id.rcvDebitTransaction);
        transactionByDateMap = GroupTransactions.getTransactionByGroup(debitTransactionList);
        adapter = new TransactionAdapter(transactionByDateMap);
        rcvTransaction.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rcvTransaction.setAdapter(adapter);
        return v;
    }
}
