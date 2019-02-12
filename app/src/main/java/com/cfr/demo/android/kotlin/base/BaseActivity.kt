package com.cfr.demo.android.kotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.cfr.demo.android.MyApplication
import com.classic.common.MultipleStatusView
import com.qmuiteam.qmui.util.QMUIStatusBarHelper


/**
   * @description: BaseActivity基类
   * @author JayGengi
   * @date  2018/10/29 0029 上午 11:57
   * @email jaygengiii@gmail.com
   */
abstract class BaseActivity : AppCompatActivity() {
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.translucent(this) // 沉浸式状态栏
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        setContentView(layoutId())
        initData()
        initView()
        start()
        initListener()


    }

    private fun initListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }


    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(this)?.watch(this)
    }

}


