package com.passkeeperoffline.ui.home.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.passkeeperoffline.R;
import com.passkeeperoffline.adapter.HomeAdapter;
import com.passkeeperoffline.base.BaseFragment;
import com.passkeeperoffline.db.AccountsModel;
import com.passkeeperoffline.db.AppDatabase;
import com.passkeeperoffline.ui.authentication.AuthActivity;
import com.passkeeperoffline.ui.home.addaccount.AddAccountFragment;
import com.passkeeperoffline.ui.home.settings.SettingsFragment;
import com.passkeeperoffline.utils.AdapterClickListener;
import com.passkeeperoffline.utils.BottomSheetMore;
import com.passkeeperoffline.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements AdapterClickListener, PopupMenu.OnMenuItemClickListener {
    @BindView(R.id.fabAdd)
    ExtendedFloatingActionButton fabAdd;
    @BindView(R.id.rvHome)
    RecyclerView rvHome;
    private int uID;
    private HomeAdapter adapter;
    private AppDatabase db;

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public String setTitle() {
        return getString(R.string.txt_pass_keeper);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        fetchData();
    }

    private void init() {
        adapter = new HomeAdapter(new ArrayList<>(), this);
        ivOverflow.setOnClickListener(view -> showPopup(toolbar));
        fabAdd.setOnClickListener(view -> changeFragment(new AddAccountFragment(), true));
    }

    private List<AccountsModel> dataModel;

    private void fetchData() {
        db = Room.databaseBuilder(Objects.requireNonNull(getActivity()).getApplicationContext(),
                AppDatabase.class, "accounts-db").allowMainThreadQueries().build();
        dataModel = db.accountsDao().getAll();
        adapter.updateData(dataModel);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter(adapter);
        Utils.getInstance().uId = dataModel.size();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setGravity(Gravity.END);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.over_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public void onClickListen(int position) {
        uID = dataModel.get(position).uid;
        BottomSheetMore sheetDialog = new BottomSheetMore(dataModel.get(position), getContext());
        sheetDialog.setActionListener(new BottomSheetMore.OnBottomItemClickListener() {
            @Override
            public void onEditClick() {
            }

            @Override
            public void onDeleteClick() {
                db.accountsDao().delete(dataModel.get(position));
                dataModel=db.accountsDao().getAll();
                adapter.updateData(dataModel);

            }
        });
        sheetDialog.show(getChildFragmentManager(), getActivity().getClass().getSimpleName());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                changeFragment(new SettingsFragment(), true);
                break;
            case R.id.logout:
                getActivity().finish();
                startActivity(new Intent(getActivity(), AuthActivity.class));

                break;

        }
        return false;
    }
}