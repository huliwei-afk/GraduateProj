package com.example.graduateproj.mainPack.mePack.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.commonUtil.WindowBarStatusUtil;
import com.example.graduateproj.mainPack.mePack.util.PreferStateUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class PreferActivity extends AppCompatActivity {
    private ImageView backIcon;
    private Switch vibrateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefer);

        WindowBarStatusUtil.setBarStatus(this, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initViews();
        initEvents();
    }

    private void initViews() {
        backIcon = findViewById(R.id.prefer_back);

        vibrateSwitch = findViewById(R.id.vibrate_state);
        vibrateSwitch.setChecked(PreferStateUtil.getInstance(this).getVibrateStateOrDefault());
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
        vibrateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferStateUtil.getInstance(PreferActivity.this).saveVibrateStateToLocal(isChecked);
            }
        });
    }
}