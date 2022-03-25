package com.example.graduateproj.commonUI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.graduateproj.R;

public class BottomNavView extends RelativeLayout {
    private ImageView bottomImage;
    private TextView bottomText;

    public BottomNavView(Context context) {
        super(context);
    }

    public BottomNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bottom_nav_icon, this);
        bottomImage = findViewById(R.id.bottom_image);
        bottomText = findViewById(R.id.bottom_text);
    }

    /**
     * 设置图片资源
     */
    public void setImageResource(int resId) {
        bottomImage.setImageResource(resId);
    }

    /**
     * 设置显示的文字
     */
    public void setText(String text) {
        bottomText.setText(text);
    }

}
