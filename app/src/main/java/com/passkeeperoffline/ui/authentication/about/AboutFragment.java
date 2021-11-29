package com.passkeeperoffline.ui.authentication.about;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseFragment;
import com.passkeeperoffline.ui.authentication.setup.SetupFragment;
import com.passkeeperoffline.utils.PreferencesHelper;

import javax.xml.validation.Validator;

import butterknife.BindView;
import butterknife.OnClick;


public class AboutFragment extends BaseFragment {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tilName)
    TextInputLayout tilName;

    @Override
    public int setLayout() {
        return R.layout.fragment_about;
    }

    @Override
    public String setTitle() {
        return "Pass Keeper";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.btNext})
    public void onClick(View v) {
        saveData();
    }

    private void saveData() {
        String mName = etName.getText().toString();
        if (TextUtils.isEmpty(mName)) {
            tilName.setError("Required");
        } else {
            PreferencesHelper.getInstance().setName(mName);
            changeFragment(new SetupFragment(), true);
        }
    }
}