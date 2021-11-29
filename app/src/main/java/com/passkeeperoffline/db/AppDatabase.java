package com.passkeeperoffline.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AccountsModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountsDao accountsDao();
}