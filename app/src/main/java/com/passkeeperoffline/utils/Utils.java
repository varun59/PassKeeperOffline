package com.passkeeperoffline.utils;

import android.content.Context;
import android.util.Base64;

import com.kazakago.cryptore.CipherAlgorithm;
import com.kazakago.cryptore.Cryptore;
import com.kazakago.cryptore.DecryptResult;
import com.kazakago.cryptore.EncryptResult;
import com.kazakago.cryptore.EncryptionPadding;

public class Utils {
    private static Utils instance;
    public int uId = 0;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }

    public Cryptore getCryptore(Context context, String alias) throws Exception {
        Cryptore.Builder builder = new Cryptore.Builder(alias, CipherAlgorithm.RSA);
        builder.setContext(context); //Need Only RSA on below API Lv22.
//    builder.setBlockMode(BlockMode.ECB); //If Needed.
        builder.setEncryptionPadding(EncryptionPadding.RSA_PKCS1); //If Needed.
        return builder.build();
    }

    public String encrypt(Context context, String plainStr) {
        byte[] plainByte = plainStr.getBytes();
        EncryptResult result = null;
        try {
            result = getCryptore(context, decryptPassword(context, PreferencesHelper.getInstance().getPassword())).encrypt(plainByte);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unauthorized";
        }
        return Base64.encodeToString(result.getBytes(), Base64.DEFAULT);
    }

    public String encryptPassword(Context context, String plainStr) {
        byte[] plainByte = plainStr.getBytes();
        EncryptResult result = null;
        try {
            result = getCryptore(context, Constants.KEY_PASSWORD).encrypt(plainByte);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unauthorized";
        }
        return Base64.encodeToString(result.getBytes(), Base64.DEFAULT);
    }

    public String decrypt(Context context, String encryptedStr) {
        byte[] encryptedByte = Base64.decode(encryptedStr, Base64.DEFAULT);
        DecryptResult result = null;
        try {
            result = getCryptore(context, decryptPassword(context, PreferencesHelper.getInstance().getPassword())).decrypt(encryptedByte, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "UnAuthorized";
        }
        return new String(result.getBytes());
    }

    public String decryptPassword(Context context, String encryptedStr) {
        byte[] encryptedByte = Base64.decode(encryptedStr, Base64.DEFAULT);
        DecryptResult result = null;
        try {
            result = getCryptore(context, Constants.KEY_PASSWORD).decrypt(encryptedByte, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "UnAuthorized";
        }
        return new String(result.getBytes());
    }
}
