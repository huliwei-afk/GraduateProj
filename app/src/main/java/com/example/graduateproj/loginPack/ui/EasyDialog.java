package com.example.graduateproj.loginPack.ui;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EasyDialog extends Dialog {
    public EasyDialog(@NonNull Context context) {
        super(context);
    }

    public EasyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected EasyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }



}
