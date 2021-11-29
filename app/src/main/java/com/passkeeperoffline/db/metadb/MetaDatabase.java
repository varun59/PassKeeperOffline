package com.passkeeperoffline.db.metadb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.passkeeperoffline.db.AccountsDao;
import com.passkeeperoffline.db.AccountsModel;

@Database(entities = {MetaModel.class}, version = 1)
public abstract class MetaDatabase extends RoomDatabase {
    public abstract MetaDao metaDao();
}