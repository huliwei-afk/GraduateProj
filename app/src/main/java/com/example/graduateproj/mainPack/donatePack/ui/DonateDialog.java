package com.example.graduateproj.mainPack.donatePack.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUI.CommonMoveDownDialog;
import com.example.graduateproj.commonUtil.RxClickUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class DonateDialog extends CommonMoveDownDialog {

    public DonateDialog(@NonNull Context context) {
        super(context);
    }

    public DonateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DonateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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
    }
}