package com.example.graduateproj.mainPack.mePack.util;

import android.text.format.DateFormat;

import java.util.Date;

public class MeCalendarUtil {

    public static String getCalendarDay() {
        return new Date().toString().substring(0, 3);
    }

    public static String getCalendarMonth() {
        String todayDateFormat = DateFormat.format("MM-dd-yyyy", System.currentTimeMillis()).toString();
        String m = todayDateFormat.substring(0, 2);
        String d = todayDateFormat.substring(3, 5);
        return m + "." + d;
    }

}
