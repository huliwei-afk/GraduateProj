package com.example.graduateproj.mainPack.mePack.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferStateUtil {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private static final String FILE_NAME = "PreferStateFile";

    private static final String VIBRATE_STATE = "VibrateState";

    private static PreferStateUtil INSTANCE;

    private PreferStateUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static PreferStateUtil getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferStateUtil(context);
        }
        return INSTANCE;
    }

    public void saveVibrateStateToLocal(boolean checked) {
        editor.putBoolean(VIBRATE_STATE, checked);
        editor.apply();
    }

    public boolean getVibrateStateOrDefault() {
        return sharedPreferences.getBoolean(VIBRATE_STATE, true);
    }
}
