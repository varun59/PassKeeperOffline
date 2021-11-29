package com.passkeeperoffline.db.metadb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.passkeeperoffline.db.AccountsModel;

import java.util.List;

@Dao
public interface MetaDao {
    @Query("SELECT * FROM MetaModel")
    List<MetaModel> getAll();

    @Insert
    void insertAll(MetaModel... metaModels);

    @Delete
    void delete(MetaModel metaModels);

    @Query("DELETE FROM MetaModel")
    public void nukeTable();
}
