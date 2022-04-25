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

import java.util.List;
import java.util.Map;

public class AllFragment extends Fragment {

    private RecyclerView rcvTransaction;
    private TransactionAdapter adapter;
    private Map<String,List<Transaction>> transactionByDateMap;
    private List<Transaction> allTransactionList;

    public AllFragment(List<Transaction> allTransactionList) {
        this.allTransactionList = allTransactionList;
    }

    public void setAllTransactionList(List<Transaction> allTransactionList) {
        this.allTransactionList = allTransactionList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.all_fragment_ui,container,false);
        TextView textView = v.findViewById(R.id.noTransaction);
        rcvTransaction = v.findViewById(R.id.rcvAllTransaction);

        if(allTransactionList.size() != 0) {
            rcvTransaction.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            transactionByDateMap = GroupTransactions.getTransactionByGroup(allTransactionList);
            System.out.println(transactionByDateMap.toString());
            adapter = new TransactionAdapter(transactionByDateMap);
            rcvTransaction.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            rcvTransaction.setAdapter(adapter);
        }

        return v;
    }
}
