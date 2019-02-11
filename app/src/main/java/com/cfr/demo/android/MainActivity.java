package com.cfr.demo.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cfr.demo.android.adapter.CNCompanyAdapterDelegate;
import com.cfr.demo.android.adapter.CompanyAdapterDelegate;
import com.cfr.demo.android.adapter.USCompanyAdapterDelegate;
import com.kevin.delegationadapter.DelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        // ① 设置 LayoutManager
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // ② 创建 DelegationAdapter 对象
        DelegationAdapter delegationAdapter = new DelegationAdapter();
        // ③ 向Adapter中注册委托Adapter
//        delegationAdapter.addDelegate(new CompanyAdapterDelegate());
        // 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(new CNCompanyAdapterDelegate());
        delegationAdapter.addDelegate(new USCompanyAdapterDelegate());
        // ④ 设置Adapter
        recyclerView.setAdapter(delegationAdapter);


        List<String> companies = new ArrayList<>();
        companies.add("🇨🇳 Baidu");
        companies.add("🇨🇳 Alibaba");
        companies.add("🇨🇳 Tencent");
        companies.add("🇺🇸 Google");
        companies.add("🇺🇸 Facebook");
        companies.add("🇺🇸 Microsoft");
        // ⑤ 设置数据
        delegationAdapter.setDataItems(companies);


    }
}
