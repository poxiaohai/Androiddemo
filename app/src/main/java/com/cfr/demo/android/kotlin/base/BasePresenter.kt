package com.cfr.demo.android.kotlin.base

/**
 *
 * @author vico
 * @date 2018-08-17
 */
interface BasePresenter {
    /**
     * 开启订阅
     */
    fun subscribe()

    /**
     * 结束订阅
     */
    fun unsubscribe()
}