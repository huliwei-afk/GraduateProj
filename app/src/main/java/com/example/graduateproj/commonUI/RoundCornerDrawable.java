package com.example.graduateproj.commonUI;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class RoundCornerDrawable extends Drawable {

    final int color;
    final float radius;
    final Paint paint;
    final RectF rectF;

    RoundCornerDrawable(int color, float radius) {
        this.color = color;
        this.radius = radius;

        this.paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(color);

        this.rectF = new RectF();
    }

    // 设置Drawable宽高
    public void setRect(int width, int height) {
        this.rectF.left = 0;
        this.rectF.top = 0;
        this.rectF.right = width;
        this.rectF.bottom = height;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(rectF, radius, radius, paint); // 画圆角矩形，现成的方法
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    @Deprecated
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
