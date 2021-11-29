package com.passkeeperoffline.ui.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseActivity;
import com.passkeeperoffline.ui.home.home.HomeFragment;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        changeFragment(new HomeFragment(),false);
    }
}