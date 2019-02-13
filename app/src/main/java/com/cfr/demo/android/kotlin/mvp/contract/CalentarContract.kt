package com.cfr.demo.android.kotlin.mvp.contract

import com.cfr.demo.android.kotlin.base.BasePresenter
import com.cfr.demo.android.kotlin.base.BaseView
import com.cfr.demo.android.kotlin.bean.CalentarDayBean
import com.cfr.demo.android.kotlin.bean.CalentarDayData
import com.cfr.demo.android.kotlin.bean.CalentarDayResult

/**
 *
 * @author vico
 * @date 2018-08-17
 */
interface CalentarContract {

    interface View : BaseView<Presenter> {
        fun showDayCalentarData(calentarDayBean: CalentarDayBean<CalentarDayResult<CalentarDayData>>)
        fun showError(errorMsg: String)
    }

    interface Presenter : BasePresenter {
        fun getDayCalentarData(date: String)
    }
}