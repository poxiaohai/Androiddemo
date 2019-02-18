package com.cfr.demo.android.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cfr.demo.android.R;
import com.cfr.demo.android.ui.WaterMarkBg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class otherActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_other);
        TextView btn=findViewById(R.id.btn);
        SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");

        List<String> labels = new ArrayList<>();
        labels.add("用户名：张三");
        labels.add("日期："+ createTimeSdf1.format(new Date()));
        labels.add("不可扩散");
        btn.setBackgroundDrawable(new WaterMarkBg(otherActivity.this,labels,-30,13));
    }
}
