package com.passkeeperoffline.utils;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;


public class SnackBarUtils {
    private static final String TAG = SnackBarUtils.class.getSimpleName();
    private static SnackBarUtils instance;
    private Snackbar snackbar;

    private SnackBarUtils() {
    }

    public static SnackBarUtils getInstance() {
        if (instance == null) {
            instance = new SnackBarUtils();
        }
        return new SnackBarUtils();
    }

    //*show snackbar
    public void showSnackBar(View parentView, String message) {
        try {
            if (parentView != null) {
                if (snackbar != null) {
                    snackbar.dismiss();
                }
                snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT);
                snackbar.getView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                View sv = snackbar.getView();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    sv.setBackground(BaseApplication.instance.getResources().getDrawable(R.drawable.snackbarbackground));
//                }
//                if (!message.equalsIgnoreCase(BaseApplication.instance.getString(R.string.txt_too_many_attampts)))
                snackbar.show();
            }
        } catch (Exception e) {
            Log.e(TAG, "showSnackBar: " + e.getMessage());
        }

    }


}
