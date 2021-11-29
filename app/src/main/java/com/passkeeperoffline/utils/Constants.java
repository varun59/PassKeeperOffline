package com.passkeeperoffline.utils;

import android.os.Environment;

public interface Constants {
    String DB_NAME = "accounts-db";
    String KEY_PASSWORD = "DROWSSAP";
    String BACKUP_NAME = "BackupMetaData";
    String BACKUP_LOCATION = Environment.getExternalStorageDirectory().getPath().toString() + "/backups";
}
