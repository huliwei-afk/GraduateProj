package com.example.graduateproj.interfaceUtil;

import com.example.graduateproj.mainPack.homePack.model.BannerImageBean;

import java.util.List;

public interface OnDataObtainListener {
    void onSuccess(BannerImageBean dataList);

    void onFailure(Throwable e);
}
