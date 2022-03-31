package com.example.graduateproj.mainPack.mePack.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.graduateproj.R;
import com.example.graduateproj.commonUtil.RxClickUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class NotificationDialog extends Dialog {

    private final String hintMessage;

    public NotificationDialog(@NonNull Context context, String message) {
        super(context, R.style.verify_dialog);

        this.hintMessage = message;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_fragment_notification_dialog);

        initView();
    }

    private void initView() {
        Button btnConfirm = findViewById(R.id.notification_dialog_confirm);
        TextView tvMessage = findViewById(R.id.notification_dialog_message);

        if (!hintMessage.isEmpty()) {
            tvMessage.setText(hintMessage);
        }

        RxClickUtil.INSTANCE.clickEvent(btnConfirm, getContext())
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe((Consumer<Object>) o -> {
                    dismiss();
                });
    }

}