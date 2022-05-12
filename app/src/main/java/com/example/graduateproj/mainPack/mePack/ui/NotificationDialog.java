package com.example.graduateproj.mainPack.mePack.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.mainPack.donatePack.util.PublishKind;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class NotificationDialog extends Dialog {

    private Activity context;
    private final String hintMessage;
    private final String buttonHint;
    private final int kind;

    public NotificationDialog(@NonNull Activity context, String message, String buttonHint, int kind) {
        super(context, R.style.verify_dialog);

        this.hintMessage = message;
        this.buttonHint = buttonHint;
        this.kind = kind;
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_fragment_notification_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        Button btnConfirm = findViewById(R.id.notification_dialog_confirm);
        if (!buttonHint.isEmpty()) {
            btnConfirm.setText(buttonHint);
        }

        TextView tvMessage = findViewById(R.id.notification_dialog_message);
        if (!hintMessage.isEmpty()) {
            tvMessage.setText(hintMessage);
        }

        RxClickUtil.INSTANCE.clickEvent(btnConfirm, getContext())
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe((Consumer<Object>) o -> {
                    dismiss();
                    if (kind != -1) {
                        AppNavigator.INSTANCE.backToMainContentActivity(context);
                    }
                });
    }

}