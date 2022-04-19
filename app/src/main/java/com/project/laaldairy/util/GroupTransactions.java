package com.project.laaldairy.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.project.laaldairy.entity.Transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupTransactions {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<String, List<Transaction>> getTransactionByGroup(List<Transaction> transactions)
    {
        return transactions.stream().collect((Collectors.groupingBy(Transaction::getDate)));
    }
}
