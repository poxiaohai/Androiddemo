package com.cfr.demo.android;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.cfr.demo.android.Image.ImageActivity;
import com.cfr.demo.android.Image.RxImagerActivity;
import com.cfr.demo.android.RecyclerView.RecyclerViewActivity;
import com.cfr.demo.android.Shadow.ShadowActivity;
import com.cfr.demo.android.Theard.ThreadActivity;
import com.cfr.demo.android.adapter.CompanyAdapterDelegate;
import com.cfr.demo.android.delegationadapter.delegationadapterActivity;
import com.cfr.demo.android.kotlin.kotlinActivity;
import com.cfr.demo.android.other.CityActivity;
import com.cfr.demo.android.other.otherActivity;
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
        companies.add("otherTextview");
        companies.add("阴影效果");
        companies.add("上传图片");
        companies.add("rxgalleryfinalDemo");
        companies.add("RecyclerView");
        companies.add("通知栏管理");
        companies.add("省市区三级联动");
        companies.add("线程通信");


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
                    case 2:
                        intent.setClass(MainActivity.this, otherActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, ShadowActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, ImageActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(MainActivity.this, RxImagerActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(MainActivity.this, RecyclerViewActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(MainActivity.this, NotificationActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(MainActivity.this, CityActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.setClass(MainActivity.this, ThreadActivity.class);
                        startActivity(intent);
                        break;


                }
            }
        });
    }


}
