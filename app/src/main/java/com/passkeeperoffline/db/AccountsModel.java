package com.passkeeperoffline.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AccountsModel {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "account_name")
    public String accountName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "additional")
    public String additional;

}
