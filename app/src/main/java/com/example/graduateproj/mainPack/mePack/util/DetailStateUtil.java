package com.example.graduateproj.mainPack.mePack.util;

import android.content.Context;
import android.content.SharedPreferences;

public class DetailStateUtil {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private static final String FILE_NAME = "DetailStateFile";
    private static final String SELF_NAME = "SelfName";
    private static final String CUS_SIGN = "CustomSign";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String QQ_NUMBER = "QQNumber";
    private static final String INSTITUTE = "Institute";

    private static DetailStateUtil INSTANCE;

    private DetailStateUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static DetailStateUtil getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DetailStateUtil(context);
        }
        return INSTANCE;
    }

    // SelfName
    public void saveSelfNameToLocal(String name) {
        editor.putString(SELF_NAME, name);
        editor.apply();
    }

    public String getLocalSelfNameOrDefault() {
        return sharedPreferences.getString(SELF_NAME, "");
    }

    // Sign
    public void saveSignToLocal(String sign) {
        editor.putString(CUS_SIGN, sign);
        editor.apply();
    }

    public String getLocalSignOrDefault() {
        return sharedPreferences.getString(CUS_SIGN, "");
    }

    // PhoneNum
    public void savePhoneNumberToLocal(String number) {
        editor.putString(PHONE_NUMBER, number);
        editor.apply();
    }

    public String getLocalPhoneNumberOrDefault() {
        return sharedPreferences.getString(PHONE_NUMBER, "");
    }

    // QQ
    public void saveQQToLocal(String qq) {
        editor.putString(QQ_NUMBER, qq);
        editor.apply();
    }

    public String getLocalQQOrDefault() {
        return sharedPreferences.getString(QQ_NUMBER, "");
    }

    // Institute
    public void saveInstituteToLocal(String institute) {
        editor.putString(INSTITUTE, institute);
        editor.apply();
    }

    public String getLocalInstituteOrDefault() {
        return sharedPreferences.getString(INSTITUTE, "");
    }

}
