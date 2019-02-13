package com.cfr.demo.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.cfr.demo.android.adapter.CompanyAdapterDelegate;
import com.cfr.demo.android.delegationadapter.delegationadapterActivity;
import com.cfr.demo.android.kotlin.kotlinActivity;
import com.kevin.delegationadapter.DelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private CompanyAdapterDelegate mCompanyAdapterDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

        mCompanyAdapterDelegate=new CompanyAdapterDelegate();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // ② 创建 DelegationAdapter 对象
        DelegationAdapter delegationAdapter = new DelegationAdapter();
        // ③ 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(mCompanyAdapterDelegate);
        // ④ 设置Adapter
        recyclerView.setAdapter(delegationAdapter);


        List<String> companies = new ArrayList<>();
        companies.add("delegationadapter");
        companies.add("kotlin");
        // ⑤ 设置数据
        delegationAdapter.setDataItems(companies);
        mCompanyAdapterDelegate.setmOnItemClieck(new CompanyAdapterDelegate.OnItemClieck() {
            @Override
            public void Clieck(int postion) {
                Intent intent=new Intent();
                switch (postion){
                    case 0:
                        intent.setClass(MainActivity.this, delegationadapterActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, kotlinActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });



    }
}
