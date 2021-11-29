package com.passkeeperoffline.ui.home.addaccount;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseFragment;
import com.passkeeperoffline.db.AccountsModel;
import com.passkeeperoffline.db.AppDatabase;
import com.passkeeperoffline.utils.Constants;
import com.passkeeperoffline.utils.SnackBarUtils;
import com.passkeeperoffline.utils.Utils;
import com.passkeeperoffline.utils.Validator;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;


public class AddAccountFragment extends BaseFragment {


    @BindView(R.id.btNext)
    Button btNext;
    @BindView(R.id.tilAccountName)
    TextInputLayout tilAccountName;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.tilAdditional)
    TextInputLayout tilAdditional;

    @Override
    public int setLayout() {
        return R.layout.fragment_add_account;
    }

    @Override
    public String setTitle() {
        return getString(R.string.txt_add_account);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    AccountsModel accountsModel;

    private void init() {
        accountsModel = new AccountsModel();


    }

    private String mAccountName, mEmail, mPassword, mAdditional;

    private void validateData() {
        AppDatabase db = Room.databaseBuilder(Objects.requireNonNull(getActivity()).getApplicationContext(),
                AppDatabase.class, Constants.DB_NAME).allowMainThreadQueries().build();

        mAccountName = tilAccountName.getEditText().getText().toString();
        mEmail = tilEmail.getEditText().getText().toString();
        mPassword = tilPassword.getEditText().getText().toString();
        mAdditional = tilAdditional.getEditText().getText().toString();

        if (Validator.getInstance().validateAccount(mAccountName, mEmail, mPassword)) {
            accountsModel.accountName = Utils.getInstance().encrypt(getContext(), mAccountName);
            accountsModel.email = Utils.getInstance().encrypt(getContext(), mEmail);
            accountsModel.uid = Utils.getInstance().uId;
            accountsModel.password = Utils.getInstance().encrypt(getContext(), mPassword);
            accountsModel.additional = Utils.getInstance().encrypt(getContext(), mAdditional);
            db.accountsDao().insertAll(accountsModel);
            getActivity().onBackPressed();
        } else switch (Integer.parseInt(Validator.ErrorMessage)) {
            case 1:
                tilAccountName.setError(getString(R.string.txt_required));
                break;
            case 2:
                tilEmail.setError(getString(R.string.txt_required));
                break;
            case 3:
                tilPassword.setError(getString(R.string.txt_required));
                break;

        }

    }

    @OnClick({R.id.btNext})
    public void onClick() {
        validateData();
    }
}