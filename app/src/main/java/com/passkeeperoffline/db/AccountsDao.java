package com.passkeeperoffline.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountsDao {
    @Query("SELECT * FROM accountsmodel")
    List<AccountsModel> getAll();

    @Query("SELECT * FROM accountsmodel WHERE uid IN (:userIds)")
    List<AccountsModel> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM accountsmodel WHERE account_name LIKE :account ")
    List<AccountsModel> findByAccount(String account);

    @Insert
    void insertAll(AccountsModel... accounts);

    @Delete
    void delete(AccountsModel account);

    @Query("DELETE FROM accountsmodel")
    public void nukeTable();
}
