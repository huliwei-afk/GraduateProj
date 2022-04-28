package com.example.graduateproj.loginPack.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUI.CommonMoveDownDialog;
import com.example.graduateproj.loginPack.util.NumberLegalUtil;

public class EasyDialog extends CommonMoveDownDialog {

    private TextView verifyCode;

    /*
     构造器
     */
    public EasyDialog(@NonNull Context context) {
        super(context);
    }

    public EasyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected EasyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    /*
     生命周期
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        showVerifyCode();
    }

    private void initViews() {
        verifyCode = findViewById(R.id.verify_code);
    }

    private void showVerifyCode() {
        verifyCode.setText(NumberLegalUtil.INSTANCE.generateRandomVerifyCode());
    }

}
