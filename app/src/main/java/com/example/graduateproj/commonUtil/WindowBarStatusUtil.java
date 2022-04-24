package com.example.graduateproj.commonUtil;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

@Deprecated
public class WindowBarStatusUtil {

    public static void setBarStatus(Activity activity, int ttfAndIconColor) {
        //清除状态栏状态
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //layout沉浸到状态栏, 状态栏中设置字体图标颜色
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | ttfAndIconColor);//白色 View.SYSTEM_UI_FLAG_LAYOUT_STABLE , 黑色 View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //绘制为透明背景的标志
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    /**
     * @param color 这是直接设置windowBackground 颜色或者图片
     */
    public static void initBar(Activity activity, int color, int navigationId) {
        //状态栏中设置字体图标颜色
        switch (navigationId) {
            case 0:
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                break;
            case 1:
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                break;
            default:
                break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 以上直接设置状态栏颜色
            activity.getWindow().setStatusBarColor(color);
        } else {
            //根布局添加占位状态栏
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            //statusBarView.setBackgroundColor(color);
            statusBarView.setBackgroundResource(color);
            decorView.addView(statusBarView, lp);
        }

    }

    /**
     * 获取状态栏高度
     *
     * @param activity context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Activity activity) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
}