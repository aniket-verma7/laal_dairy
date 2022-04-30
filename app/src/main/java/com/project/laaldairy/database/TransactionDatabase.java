package com.project.laaldairy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.laaldairy.dao.TransactionDao;
import com.project.laaldairy.entity.Transaction;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDao getTransactionDao();

    private static TransactionDatabase instance;

    public static TransactionDatabase getInstance(Context context)
    {
        if(instance == null) {
            synchronized (TransactionDatabase.class) {
                instance = Room.databaseBuilder(context, TransactionDatabase.class, "transaction_database").allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}
