package com.example.graduateproj.mainPack.homePack.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.graduateproj.interfaceUtil.OnBannerImageLoadListener;
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean;

public class HomeBannerAdapter extends PagerAdapter {
    private final BannerImageBean bean;
    private final OnBannerImageLoadListener onBannerImageLoadListener;

    public HomeBannerAdapter(BannerImageBean bean, OnBannerImageLoadListener onBannerImageLoadListener) {
        this.bean = bean;
        this.onBannerImageLoadListener = onBannerImageLoadListener;
    }

    @Override
    public int getCount() {
        return (bean.getData().size() * 10000 * 100);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        position = position % bean.getData().size();
        if(onBannerImageLoadListener != null) {
            onBannerImageLoadListener.loadBannerImage(bean, position, imageView);
        }
        container.addView(imageView);
        return imageView;
    }
}
