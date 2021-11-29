package com.passkeeperoffline.ui.home.settings;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.passkeeperoffline.R;
import com.passkeeperoffline.adapter.SettingsAdapter;
import com.passkeeperoffline.base.BaseFragment;
import com.passkeeperoffline.db.AppDatabase;
import com.passkeeperoffline.db.DbUtils;
import com.passkeeperoffline.db.metadb.MetaDatabase;
import com.passkeeperoffline.utils.AdapterClickListener;
import com.passkeeperoffline.utils.Constants;
import com.passkeeperoffline.utils.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class SettingsFragment extends BaseFragment implements AdapterClickListener {
    @BindView(R.id.rvSettings)
    RecyclerView recyclerView;
    private SettingsAdapter adapter;
    private AppDatabase db;
    private MetaDatabase metaDb;

    @Override
    public int setLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    public String setTitle() {
        return getString(R.string.txt_settings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setAdapter();
    }

    private void init() {
        db = Room.databaseBuilder(requireActivity().getApplicationContext(),
                AppDatabase.class, Constants.DB_NAME).allowMainThreadQueries().build();
        metaDb = Room.databaseBuilder(requireActivity().getApplicationContext(),
                MetaDatabase.class, "metaDb").allowMainThreadQueries().build();
    }

    private void setAdapter() {
        List<String> list = new ArrayList<>();
        list.add("Enable Biometric/Fingerprint Login");
        list.add("Create Backup");
        list.add("Restore Backup");
        list.add("Clear Data");
        adapter = new SettingsAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickListen(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                createBackup();
                break;
            case 2:
                restoreBackup();
                break;
            case 3:
                clearDb();
                break;
        }
    }

    private void createBackup() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(getContext()/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                DbUtils.getInstance().saveToDb(getContext(), db, getView());
            }
        });
    }

    private void restoreBackup() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(getContext()/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
//                DbUtils.getInstance().restoreBackUpName(metaDb, getView());
                DbUtils.getInstance().restoreDb(db, getView());
            }
        });

    }

    private void clearDb() {
        metaDb.clearAllTables();
        db.clearAllTables();
    }
}