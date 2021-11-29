package com.passkeeperoffline.db.metadb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MetaModel {

    @NonNull
    @PrimaryKey
    public String LastBackupName;


}
