package com.project.laaldairy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.laaldairy.R;
import com.project.laaldairy.entity.Transaction;

import java.util.List;

public class SubTransactionAdapter extends RecyclerView.Adapter<SubTransactionAdapter.Holder> {

    private List<Transaction> transactions;

    public SubTransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_sub_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        TextView title,category,price,time;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.transaction_title);
            category = itemView.findViewById(R.id.transaction_category);
            price = itemView.findViewById(R.id.transaction_price);
            time = itemView.findViewById(R.id.transaction_time);
        }
    }
}
