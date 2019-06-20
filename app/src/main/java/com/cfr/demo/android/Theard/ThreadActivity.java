package com.cfr.demo.android.Theard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cfr.demo.android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadActivity extends AppCompatActivity {
    private Handler chlidhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                chlidhandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Toast.makeText(ThreadActivity.this, (String) msg.obj,Toast.LENGTH_LONG).show();
                    }
                };
                Looper.loop();

            }
        }).start();

        findViewById(R.id.mBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message ms=Message.obtain();
                        ms.what=1;
                        ms.obj="11111";
                        chlidhandler.sendMessage(ms);
                    }
                }).start();
            }
        });
    }

    @OnClick({R.id.mbtn1, R.id.mbtn2, R.id.mBtn3})
    public void onclieck(View view) {
        switch (view.getId()) {
            case R.id.mbtn1:

                break;
            case R.id.mbtn2:
                break;
            case R.id.mBtn3:

                break;
        }
    }

}
