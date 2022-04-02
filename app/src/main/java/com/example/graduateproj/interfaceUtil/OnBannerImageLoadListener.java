package com.example.graduateproj.interfaceUtil;

import android.view.View;

import com.example.graduateproj.mainPack.homePack.model.BannerImageBean;

public interface OnBannerImageLoadListener {
    void loadBannerImage(BannerImageBean bean, int position, View imageView);
}
