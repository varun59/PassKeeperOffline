package com.passkeeperoffline.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static BaseApplication instance;

    public  BaseApplication getInstance() {
        if (instance == null) {
            instance =  this;
        }
        return instance;
    }

    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
    }
}
