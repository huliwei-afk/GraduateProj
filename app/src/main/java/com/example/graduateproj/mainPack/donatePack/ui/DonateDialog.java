package com.example.graduateproj.mainPack.donatePack.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUI.CommonMoveDownDialog;
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.mainPack.donatePack.presenter.DonatePresenter;
import com.example.graduateproj.mainPack.donatePack.util.PublishKind;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class DonateDialog extends CommonMoveDownDialog {

    private Activity context;

    public DonateDialog(@NonNull Activity context) {
        super(context);
    }

    public DonateDialog(@NonNull Activity context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected DonateDialog(@NonNull Activity context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    private void initViews() {
        RelativeLayout priceRelative = findViewById(R.id.add_price_item);
        RelativeLayout freeRelative = findViewById(R.id.add_free_item);
        ImageView dismissIcon = findViewById(R.id.dismiss_icon);

        RxClickUtil.INSTANCE.clickEvent(dismissIcon, getContext())
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe((Consumer<Object>) o -> {
                    dismiss();
                });

        RxClickUtil.INSTANCE.clickEvent(freeRelative, getContext())
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe((Consumer<Object>) o -> {
                    AppNavigator.INSTANCE.openSaleInfoActivity(context, PublishKind.FREE);
                    dismiss();
                });

        RxClickUtil.INSTANCE.clickEvent(priceRelative, getContext())
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe((Consumer<Object>) o -> {
                    AppNavigator.INSTANCE.openSaleInfoActivity(context, PublishKind.PRICE);
                    dismiss();
                });
    }
}