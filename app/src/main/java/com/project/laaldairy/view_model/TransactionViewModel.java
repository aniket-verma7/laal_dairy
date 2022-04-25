package com.project.laaldairy.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.project.laaldairy.dao.TransactionDao;
import com.project.laaldairy.database.TransactionDatabase;
import com.project.laaldairy.entity.Transaction;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel
{
    private MutableLiveData<List<Transaction>> transactions;
    private TransactionDao dao;

    public TransactionViewModel(@NonNull Application application)
    {
        super(application);
        dao = TransactionDatabase.getInstance(application).getTransactionDao();
    }

    public MutableLiveData<List<Transaction>> getTransaction()
    {
        if(transactions == null)
        {
            transactions = new MutableLiveData<>();
            loadTransactions();
        }
        return transactions;
    }

    private void loadTransactions() {
        transactions.setValue(dao.getAllTransaction());
    }

    public void addTransaction(Transaction transaction)
    {
        dao.insert(transaction);
        loadTransactions();
    }

    public void removeTransaction(Transaction transaction)
    {
        dao.delete(transaction);
    }

    public void updateTransaction(Transaction transaction)
    {
        dao.update(transaction);
    }
}
