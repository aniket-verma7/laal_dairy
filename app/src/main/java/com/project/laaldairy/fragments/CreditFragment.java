package com.project.laaldairy.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.laaldairy.R;
import com.project.laaldairy.adapter.TransactionAdapter;
import com.project.laaldairy.entity.Transaction;
import com.project.laaldairy.util.GroupTransactions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CreditFragment extends Fragment {

    private RecyclerView rcvTransaction;
    private TransactionAdapter adapter;
    private List<Transaction> creditTransactionList;
    private Map<String,List<Transaction>> transactionByDateMap;
    private TextView textView;

    public CreditFragment(List<Transaction> creditTransactions) {
        transactionByDateMap = new HashMap<>();
        this.creditTransactionList = creditTransactions;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setCreditTransactionList(List<Transaction> creditTransactionList) {

        if(adapter != null){
            this.creditTransactionList = creditTransactionList;
            transactionByDateMap = GroupTransactions.getTransactionByGroup(creditTransactionList);
            adapter.updateAdapter(transactionByDateMap);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.credit_fragment_ui,container,false);
        textView = v.findViewById(R.id.noTransaction);
        rcvTransaction = v.findViewById(R.id.rcvCreditTransaction);

        if(creditTransactionList.size() != 0) {
            setupAdapter();
        }
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupAdapter()
    {
        rcvTransaction.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
        transactionByDateMap = GroupTransactions.getTransactionByGroup(creditTransactionList);
        adapter = new TransactionAdapter(transactionByDateMap);
        rcvTransaction.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rcvTransaction.setAdapter(adapter);
    }

}
