package com.project.laaldairy.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.laaldairy.entity.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {

    @Query("Select * from 'transaction'")
    List<Transaction> getAllTransaction();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction... transactions);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);
}
