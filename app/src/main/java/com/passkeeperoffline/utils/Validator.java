package com.passkeeperoffline.utils;


import android.text.TextUtils;
import android.util.Patterns;

import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseApplication;

public class Validator {

    public static String ErrorMessage = "";

    private static Validator instance;

    private Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public boolean validateSetup(String mPassword, String mConfirmPassword, String mQuestion, String mAnswer, Boolean checked) {
        if (TextUtils.isEmpty(mPassword)) {
            ErrorMessage = BaseApplication.instance.getString(R.string.msg_master_password);
            return false;
        } else if (TextUtils.isEmpty(mConfirmPassword)) {
            ErrorMessage = BaseApplication.instance.getString(R.string.msg_confirm_password);
            return false;
        } else if (TextUtils.isEmpty(mQuestion)) {
            ErrorMessage = BaseApplication.instance.getString(R.string.msg_question);
            return false;
        } else if (TextUtils.isEmpty(mAnswer)) {
            ErrorMessage = BaseApplication.instance.getString(R.string.msg_answer);
            return false;
        } else if (!mPassword.equals(mConfirmPassword)) {
            ErrorMessage = BaseApplication.instance.getString(R.string.msg_password_match);
            return false;
        } else if (!checked) {
            ErrorMessage = BaseApplication.instance.getString(R.string.msg_read);
            return false;
        } else return true;
    }

    public boolean validateAccount(String mAccountName, String mEmail, String mPassword) {
        if (TextUtils.isEmpty(mAccountName)) {
            ErrorMessage = "1";
            return false;
        } else if (TextUtils.isEmpty(mEmail)) {
            ErrorMessage = "2";
            return false;
        } else if (TextUtils.isEmpty(mPassword)) {
            ErrorMessage = "3";
            return false;
        } else return true;

    }
}
