package com.cfr.demo.android.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.cfr.demo.android.R;
import com.cfr.demo.android.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = findViewById(R.id.mRecyview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        for (int i = 0; i < 10; i++) {
            data.add("item" + i);
        }
        mRecyclerViewAdapter = new RecyclerViewAdapter(data, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent e) {
                if (e != null) {
                    View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (view != null) {
                        RecyclerViewAdapter.viewHolder viewHolder = (RecyclerViewAdapter.viewHolder) mRecyclerView.getChildViewHolder(view);
                        mRecyclerView.requestDisallowInterceptTouchEvent(viewHolder.isTouchNsv(viewHolder.mScrollView,e.getRawX(), e.getRawY()));
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }

}