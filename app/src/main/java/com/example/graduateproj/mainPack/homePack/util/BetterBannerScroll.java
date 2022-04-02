package com.example.graduateproj.mainPack.homePack.util;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class BetterBannerScroll extends Scroller {
    //切换动画时长(单位:毫秒)
    private static final int scrollDuration = 2000;
    private static final Interpolator sInterpolator = t -> {
        t -= 1.0f;
        return t * t * t * t * t + 1.0f;
    };
    public boolean noDuration;

    /*
     * 让调用层控制是否延时
     */
    public void setNoDuration(boolean noDuration) {
        this.noDuration = noDuration;
    }

    public BetterBannerScroll(Context context) {
        this(context, sInterpolator);
    }

    public BetterBannerScroll(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (noDuration) {
            super.startScroll(startX, startY, dx, dy, 0);
        } else {
            //默认延时
            super.startScroll(startX, startY, dx, dy, scrollDuration);
        }
    }
}
