package com.example.graduateproj.mainPack.mePack.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class PreferActivity extends AppCompatActivity {
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefer);

        initViews();
        initEvents();
    }

    private void initViews() {
        backIcon = findViewById(R.id.prefer_back);
    }

    private void initEvents() {
        RxClickUtil.INSTANCE.clickEvent(backIcon, this)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        AppNavigator.INSTANCE.backToMainContentActivity(PreferActivity.this);
                    }
                });

    }
}