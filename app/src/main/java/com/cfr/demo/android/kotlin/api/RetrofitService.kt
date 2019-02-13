package com.cfr.demo.android.kotlin.api

import com.cfr.demo.android.kotlin.bean.CalentarDayBean
import com.cfr.demo.android.kotlin.bean.CalentarDayData
import com.cfr.demo.android.kotlin.bean.CalentarDayResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    /**
     * 获取当天的详细信息
     */
    @GET("calendar/day")
    fun calenderDay(
            @Query("date") date: String,
            @Query("key") key: String
    ): Observable<CalentarDayBean<CalentarDayResult<CalentarDayData>>>
}