package com.cfr.demo.android.Image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cfr.demo.android.MainActivity;
import com.cfr.demo.android.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.utils.Logger;

public class ImageActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mTextView=findViewById(R.id.openImage);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxGalleryFinalApi instance = RxGalleryFinalApi.getInstance(ImageActivity.this);
                //快速打开单选图片,flag使用true不裁剪
                RxGalleryFinalApi
                        .openRadioSelectImage(ImageActivity.this, new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent o) throws Exception {
//                                Toast.makeText(ImageActivity.this,,Toast.LENGTH_LONG).show();
                            requestData(o.getResult().getOriginalPath());
                            }
                        }, true);

            }
        });
    }

    private void requestData(String originalPath) {
        String base64str=imageToBase64(originalPath);
        Log.e("base64str======>",base64str);
        RequestParams params = new RequestParams("http://192.168.199.171:8080/xxshop_war_exploded/app/Image/UploadImage");
        params.addParameter("fileStr",base64str);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Toast.makeText(ImageActivity.this,result,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 将图片转换成Base64编码的字符串
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data,Base64.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}
