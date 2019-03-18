package com.cfr.demo.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class newRecyclerView extends RecyclerView {

    public newRecyclerView(@NonNull Context context) {
       super(context,null);
    }

    public newRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public newRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
