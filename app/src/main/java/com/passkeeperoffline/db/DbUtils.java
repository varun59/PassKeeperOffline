package com.passkeeperoffline.db;

import android.content.Context;
import android.os.Environment;
import android.view.View;

import com.passkeeperoffline.db.metadb.MetaDatabase;
import com.passkeeperoffline.db.metadb.MetaModel;
import com.passkeeperoffline.utils.Constants;
import com.passkeeperoffline.utils.PreferencesHelper;
import com.passkeeperoffline.utils.SnackBarUtils;
import com.passkeeperoffline.utils.Utils;

import java.util.List;

import ir.androidexception.roomdatabasebackupandrestore.Backup;
import ir.androidexception.roomdatabasebackupandrestore.OnWorkFinishListener;
import ir.androidexception.roomdatabasebackupandrestore.Restore;

public class DbUtils {

    private static DbUtils instance;

    public static DbUtils getInstance() {
        if (instance == null)
            instance = new DbUtils();
        return instance;
    }

    public void saveToDb(Context context, AppDatabase database, View v) {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = "Backup" + tsLong.toString();
        PreferencesHelper.getInstance().setBackupName(ts);
        new Backup.Init()
                .database(database)
                .path(Constants.BACKUP_LOCATION)
                .fileName("encrypted_backup")
//                .secretKey(Utils.getInstance().decryptPassword(context,PreferencesHelper.getInstance().getPassword())) //optional
                .onWorkFinishListener(new OnWorkFinishListener() {
                    @Override
                    public void onFinished(boolean success, String message) {
                        SnackBarUtils.getInstance().showSnackBar(v, message);
                    }
                })
                .execute();
    }

    public void saveBackUpName(MetaDatabase database, View v) {
        new Backup.Init()
                .database(database)
                .path(Constants.BACKUP_LOCATION)
                .fileName(Constants.BACKUP_NAME)
//                .secretKey("akash") //optional
                .onWorkFinishListener(new OnWorkFinishListener() {
                    @Override
                    public void onFinished(boolean success, String message) {
                        // do anything
                        SnackBarUtils.getInstance().showSnackBar(v, message);

                    }
                })
                .execute();
    }


    public void restoreDb(AppDatabase database, View v) {
        new Restore.Init()
                .database(database)
                .backupFilePath(Constants.BACKUP_LOCATION + "/" + "encrypted_backup")
//                .secretKey(PreferencesHelper.getInstance().getAnswer()) // if your backup file is encrypted, this parameter is required
                .onWorkFinishListener(new OnWorkFinishListener() {
                    @Override
                    public void onFinished(boolean success, String message) {
                        // do anything
                        SnackBarUtils.getInstance().showSnackBar(v, message);

                    }
                })
                .execute();
    }

    public void restoreBackUpName(MetaDatabase database, View v) {
        new Restore.Init()
                .database(database)
                .backupFilePath(Constants.BACKUP_LOCATION + "/" + Constants.BACKUP_NAME)
//                .secretKey("akash") // if your backup file is encrypted, this parameter is required
                .onWorkFinishListener(new OnWorkFinishListener() {
                    @Override
                    public void onFinished(boolean success, String message) {
                        // do anything
                        List<MetaModel> metaList = database.metaDao().getAll();
                        String lastName = metaList.get(metaList.size() - 1).LastBackupName;
                        PreferencesHelper.getInstance().setBackupName(lastName);
                        SnackBarUtils.getInstance().showSnackBar(v, message);

                    }
                })
                .execute();
    }


}
