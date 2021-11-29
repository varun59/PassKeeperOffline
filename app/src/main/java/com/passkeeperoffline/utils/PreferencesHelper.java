package com.passkeeperoffline.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.passkeeperoffline.R;
import com.passkeeperoffline.base.BaseApplication;

public class PreferencesHelper {
    private static final String TAG = PreferencesHelper.class.getSimpleName();
    private static PreferencesHelper instance;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    SharedPreferences prefToken;
    private SharedPreferences.Editor editorToken;


    private PreferencesHelper() {
        pref = BaseApplication.instance.
                getSharedPreferences(BaseApplication.instance.getPackageName().toUpperCase(), Context.MODE_MULTI_PROCESS);
        editor = pref.edit();

        prefToken = BaseApplication.instance.
                getSharedPreferences(BaseApplication.instance.getPackageName().toUpperCase(), Context.MODE_PRIVATE);
        editorToken = prefToken.edit();


    }

    public static PreferencesHelper getInstance() {
        if (instance == null)
            instance = new PreferencesHelper();
        return instance;
    }

    //* Save pref
    public void savePrefValue(String key, Object value) {
        delete(key);
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
        }
        editor.commit();
    }

    //*delete specific pref
    public void delete(String key) {
        if (pref.contains(key)) {
            editor.remove(key).commit();
        }
    }

    //*get specific Pref
    public <T> T getPrefValue(String key, T defValue) {
        T returnValue = (T) pref.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public void cleanPref() {
        pref.edit().clear().apply();
    }

    //*====================User Details==============================

    public void setSesstion(int Session) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_session), Session);

    }

    public int getSession() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_session), 0);
    }

    public void setBiometric(int Session) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_biometric), Session);

    }

    public int getBiometric() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_biometric), 0);
    }

    public void setName(String name) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_name), name);

    }

    public String getName() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_name), "");
    }


    public void setPassword(String password) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_password), password);

    }

    public String getPassword() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_password), "");
    }


    public void setQuestion(String question) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_question), question);

    }

    public String getQuestion() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_question), "");
    }

    public void setAnswer(String question) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_answer), question);

    }

    public String getAnswer() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_answer), "");
    }

    public void setBackupName(String question) {
        savePrefValue(BaseApplication.instance.getString(R.string.key_backup), question);

    }

    public String getBackupName() {
        return getPrefValue(BaseApplication.instance.getString(R.string.key_backup), "");
    }


}
