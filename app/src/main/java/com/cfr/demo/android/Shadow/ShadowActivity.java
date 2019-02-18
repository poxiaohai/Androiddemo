package com.cfr.demo.android.Shadow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cfr.demo.android.R;

import cn.davidsu.library.ShadowConfig;
import cn.davidsu.library.ShadowHelper;

public class ShadowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);
        TextView mTextView=findViewById(R.id.mTextView);
        ShadowConfig.Builder config = new ShadowConfig.Builder()
                .setColor(Color.parseColor("#ed6262"))//View颜色
                .setShadowColor(Color.parseColor("#260000"))//阴影颜色
//                .setGradientColorArray("")//如果View是渐变色，则设置color数组
                .setRadius(0)//圆角
                .setOffsetX(2)//横向偏移
                .setOffsetY(2);//纵向偏移

        ShadowHelper.setShadowBgForView(mTextView, config);
    }
}
