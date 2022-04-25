package com.project.laaldairy.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.laaldairy.R;
import com.project.laaldairy.entity.Transaction;
import com.project.laaldairy.util.DateFormatter;

import java.text.DecimalFormat;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.title.setText(transactions.get(position).getTitle());
        holder.category.setText(transactions.get(position).getCategory());
        double amount = transactions.get(position).getAmount();
        holder.price.setTextColor((amount>=0)? Color.GREEN:Color.RED);
        StringBuilder formattedAmount = new StringBuilder((amount>=0)?"+ $ ":"- $ ");
        amount = (amount<0)?amount*(-1):amount;
        formattedAmount.append(String.format("%.2f", amount));
        holder.price.setText(formattedAmount.toString());
        holder.time.setText(transactions.get(position).getTime());
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
