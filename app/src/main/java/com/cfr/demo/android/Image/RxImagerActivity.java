package com.cfr.demo.android.Image;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cfr.demo.android.MainActivity;
import com.cfr.demo.android.R;

import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.Logger;

public class RxImagerActivity extends AppCompatActivity {
    private TextView mTvPhotograph;
    private  TextView video;
    private  TextView Tailoring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_imager);
        mTvPhotograph=findViewById(R.id.mTvPhotograph);
        video=findViewById(R.id.video);
        Tailoring=findViewById(R.id.Tailoring);
        mTvPhotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelectRadioMethod(1);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openVideoSelectRadioMethod();
                openVideoSelectMultiMethod(2);
            }
        });
        Tailoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCrop();
            }
        });
    }

    /**
     * OPEN 图片单选实现方法
     * <p>
     * 默认使用 第三个 ，如果运行sample,可自行改变Type，运行Demo查看效果
     */
    private void openImageSelectRadioMethod(int type) {
        RxGalleryFinalApi instance = RxGalleryFinalApi.getInstance(RxImagerActivity.this);
        switch (type) {
            case 0:

                //打开单选图片，默认参数
                instance
                        .setImageRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                Logger.i("单选图片的回调");
                            }
                        }).open();

                break;
            case 1:

                //设置自定义的参数
                instance
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_IMAGE, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
                        .setImageRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                Logger.i("单选图片的回调");
                            }
                        }).open();

                break;
            case 2:

                //快速打开单选图片,flag使用true不裁剪
                RxGalleryFinalApi
                        .openRadioSelectImage(RxImagerActivity.this, new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent o) throws Exception {
                                Logger.i("单选图片的回调");
                            }
                        }, true);

                break;
            case 3:

                //单选，使用RxGalleryFinal默认设置，并且带有裁剪
                instance
                        .openGalleryRadioImgDefault(
                                new RxBusResultDisposable<ImageRadioResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                        Logger.i("只要选择图片就会触发");
                                    }
                                })
                        .onCropImageResult(
                                new IRadioImageCheckedListener() {
                                    @Override
                                    public void cropAfter(Object t) {
                                        Logger.i("裁剪完成");
                                    }

                                    @Override
                                    public boolean isActivityFinish() {
                                        Logger.i("返回false不关闭，返回true则为关闭");
                                        return true;
                                    }
                                });

                break;
        }
    }


    /**
     * 视频单选回调
     */
    private void openVideoSelectRadioMethod() {
        RxGalleryFinalApi
                .getInstance(RxImagerActivity.this)
                .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
                .setVDRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        Toast.makeText(getApplicationContext(), imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
                    }
                })
                .open();
    }
    /**
     * 视频多选回调
     */
    private void openVideoSelectMultiMethod(int type) {
        switch (type) {
            case 0:

                //使用默认的参数
                RxGalleryFinalApi
                        .getInstance(this)
                        .setVDMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选视频的回调");
                                    }
                                }).open();

                break;
            case 1:

                //使用自定义的参数
                RxGalleryFinalApi
                        .getInstance(this)
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_MULTI)
                        .setVDMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选视频的回调");
                                    }
                                }).open();

                break;
            case 2:

                //直接打开
                RxGalleryFinalApi
                        .openMultiSelectVD(this, new RxBusResultDisposable<ImageMultipleResultEvent>() {
                            @Override
                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                Logger.i("多选视频的回调");
                            }
                        });

                break;
        }
    }

    /**
     * 直接裁剪  or  拍照并裁剪( 查看 onActivityResult())
     */
    private void openCrop() {

            SimpleRxGalleryFinal.get().init(
                    new SimpleRxGalleryFinal.RxGalleryFinalCropListener() {
                        @NonNull
                        @Override
                        public Activity getSimpleActivity() {
                            return RxImagerActivity.this;
                        }

                        @Override
                        public void onCropCancel() {
                            Toast.makeText(getSimpleActivity(), "裁剪被取消", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCropSuccess(@Nullable Uri uri) {
                            Toast.makeText(getSimpleActivity(), "裁剪成功：" + uri, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCropError(@NonNull String errorMessage) {
                            Toast.makeText(getSimpleActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
            ).openCamera();

    }
}
