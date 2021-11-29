package com.passkeeperoffline.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseActivity;
import com.passkeeperoffline.ui.authentication.about.AboutFragment;
import com.passkeeperoffline.ui.authentication.login.LoginFragment;
import com.passkeeperoffline.ui.home.activity.HomeActivity;
import com.passkeeperoffline.utils.PreferencesHelper;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        if (PreferencesHelper.getInstance().getSession() == 1) {
            changeFragment(new LoginFragment(), false);
//            startActivity(new Intent(this, HomeActivity.class));
//            finish();
        } else
            changeFragment(new AboutFragment(), false);

    }
}