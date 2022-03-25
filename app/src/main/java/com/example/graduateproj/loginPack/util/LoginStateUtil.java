package com.example.graduateproj.loginPack.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginStateUtil {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private static final String FILE_NAME = "LoginStateFile";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String PASS_WORD = "PassWord";

    private static LoginStateUtil INSTANCE;

    private LoginStateUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static LoginStateUtil getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LoginStateUtil(context);
        }
        return INSTANCE;
    }

    public void savePhoneNumberToLocal(String number) {
        editor.putString(PHONE_NUMBER, number);
        editor.apply();
    }

    public String getLocalPhoneNumberOrNull() {
        return sharedPreferences.getString(PHONE_NUMBER, null);
    }

    public void savePasswordToLocal(String password) {
        editor.putString(PASS_WORD, password);
        editor.apply();
    }

    public String getLocalPassWordOrNull() {
        return sharedPreferences.getString(PASS_WORD, null);
    }

    

}
