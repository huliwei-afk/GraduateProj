package com.example.graduateproj.loginPack.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginStateUtil {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private static final String FILE_NAME = "LoginStateFile";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String PASS_WORD = "PassWord";
    private static final String LOGIN_STATE = "LoginState";

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

    public String getLocalPhoneNumberOrDefault() {
        return sharedPreferences.getString(PHONE_NUMBER, "");
    }

    public void savePasswordToLocal(String password) {
        editor.putString(PASS_WORD, password);
        editor.apply();
    }

    public String getLocalPassWordOrDefault() {
        return sharedPreferences.getString(PASS_WORD, "");
    }

    public void saveLoginStateToLocal(boolean state) {
        editor.putBoolean(LOGIN_STATE, state);
        editor.apply();
    }

    public boolean getLocalLoginStateOrDefault() {
        return sharedPreferences.getBoolean(LOGIN_STATE, false);
    }

}
