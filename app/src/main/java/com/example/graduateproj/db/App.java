package com.example.graduateproj.db;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppDataBase.Companion.getDatabase(getApplicationContext());
    }
}
