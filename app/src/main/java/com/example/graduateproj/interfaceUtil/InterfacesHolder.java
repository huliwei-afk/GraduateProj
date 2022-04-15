package com.example.graduateproj.interfaceUtil;

import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean;
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean;

public class InterfacesHolder {
    public interface OnBannerDataObtainListener {
        void onSuccess(BannerImageBean dataList);

        void onFailure(Throwable e);
    }

    public interface OnDonateDataObtainListener {
        void onNext(DonateJsonBean dataList);

        void onFailure(Throwable e);
    }
}


