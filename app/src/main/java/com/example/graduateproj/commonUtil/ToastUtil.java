package com.example.graduateproj.commonUtil;

import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

public class ToastUtil {

    public static void showToastCenter(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToastBottom(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showToastBelowCenter(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        toast.setGravity(Gravity.BOTTOM, 0, size.y / 3);
        toast.show();
    }
}
