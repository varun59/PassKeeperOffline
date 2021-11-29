package com.passkeeperoffline.ui.authentication.setup;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kazakago.cryptore.CipherAlgorithm;
import com.kazakago.cryptore.Cryptore;
import com.kazakago.cryptore.DecryptResult;
import com.kazakago.cryptore.EncryptResult;
import com.kazakago.cryptore.EncryptionPadding;
import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseFragment;
import com.passkeeperoffline.ui.authentication.login.LoginFragment;
import com.passkeeperoffline.utils.PreferencesHelper;
import com.passkeeperoffline.utils.Utils;
import com.passkeeperoffline.utils.Validator;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class SetupFragment extends BaseFragment {
    @BindView(R.id.etMasterPassword)
    TextInputEditText etMasterPassword;
    @BindView(R.id.etConfirmPassword)
    TextInputEditText etConfirmPassword;
    @BindView(R.id.etQuestion)
    TextInputEditText etQuestion;
    @BindView(R.id.etAnswer)
    TextInputEditText etAnswer;
    @BindView(R.id.cbCheck)
    MaterialCheckBox cbCheck;
    private String mPassword, mConfirmPassword, mQuestion, mAnswer;


    @Override
    public int setLayout() {
        return R.layout.fragment_setup;
    }

    @Override
    public String setTitle() {
        return "Pass Keeper";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {

    }


    @OnClick({R.id.btNext})
    public void onClick(View v) {
        saveData(v);
    }

    private void saveData(View v) {
        String encryptedPass = "";
        mPassword = etMasterPassword.getText().toString();
        mConfirmPassword = etConfirmPassword.getText().toString();
        mQuestion = etQuestion.getText().toString();
        mAnswer = etAnswer.getText().toString();
        if (Validator.getInstance().validateSetup(mPassword, mConfirmPassword, mQuestion, mAnswer, cbCheck.isChecked())) {
            PreferencesHelper.getInstance().setPassword(Utils.getInstance().encryptPassword(getContext(), mPassword));
            PreferencesHelper.getInstance().setAnswer(Utils.getInstance().encrypt(getContext(), mAnswer));
            PreferencesHelper.getInstance().setQuestion(mQuestion);
            PreferencesHelper.getInstance().setSesstion(1);
            clearBackStack();
            changeFragment(new LoginFragment(), false);

        } else Snackbar.make(v, Validator.ErrorMessage, Snackbar.LENGTH_SHORT).show();

    }
}