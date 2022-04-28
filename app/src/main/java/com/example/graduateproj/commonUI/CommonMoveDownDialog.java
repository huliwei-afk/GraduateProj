package com.example.graduateproj.commonUI;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CommonMoveDownDialog extends Dialog {

    /** 记录按下的坐标点（起始点）**/
    private float mPosX = 0;
    private float mPosY = 0;
    /** 记录移动后抬起坐标点（终点）**/
    private float mCurPosX = 0;
    private float mCurPosY = 0;


    public CommonMoveDownDialog(@NonNull Context context) {
        super(context);
    }

    public CommonMoveDownDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommonMoveDownDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                if (mCurPosY - mPosY > 0 && (Math.abs(mCurPosY - mPosY) > 25)) {
                    dismiss();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
