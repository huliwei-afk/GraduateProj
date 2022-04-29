package com.example.graduateproj.mainPack.homePack.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private final int top;
    private final int leftAndRight;

    public GridItemDecoration(int top, int leftAndRight) {
        this.top = top;
        this.leftAndRight = leftAndRight;
    }

    @Override
    @Deprecated
    public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = top;
        outRect.bottom = top;
        outRect.left = leftAndRight;
        outRect.right = leftAndRight;
    }
}