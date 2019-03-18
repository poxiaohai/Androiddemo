package com.cfr.demo.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cfr.demo.android.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    private ArrayList<String> data;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_adapter_layout_1, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        String str = null;
        for (int j = 0; j < 10; j++) {
            str = str + "item" + j + "\n";
        }

        viewHolder.mTextView.setText(str);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ScrollView mScrollView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.mTextView);
            mScrollView = itemView.findViewById(R.id.mScrollView);
        }

        public boolean isTouchNsv(ScrollView mall_nsv, float x, float y) {
            int[] pos = new int[2]; //获取sv在屏幕上的位置
            mall_nsv.getLocationOnScreen(pos);
            int width = mall_nsv.getMeasuredWidth();
            int height = mall_nsv.getMeasuredHeight();
            return x >= pos[0] && x <= pos[0] + width && y >= pos[1] && y <= pos[1] + height;
        }
    }


}
