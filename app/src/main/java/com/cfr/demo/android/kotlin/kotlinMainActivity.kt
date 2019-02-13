package com.cfr.demo.android.kotlin

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cfr.demo.android.R
import com.cfr.demo.android.kotlin.bean.CalentarDayBean
import com.cfr.demo.android.kotlin.bean.CalentarDayData
import com.cfr.demo.android.kotlin.bean.CalentarDayResult
import com.cfr.demo.android.kotlin.mvp.contract.CalentarContract
import com.cfr.demo.android.kotlin.mvp.presenter.CalentarPresenter
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import org.jetbrains.anko.toast

class kotlinMainActivity : AppCompatActivity(), CalentarContract.View  {
    override lateinit var presenter: CalentarContract.Presenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        presenter = CalentarPresenter(this)
        selectButton.setOnClickListener {
            titleTextView.visibility = View.GONE
            selectButton.visibility = View.GONE
            contentTextView.visibility = View.GONE
            datePicker.visibility = View.VISIBLE
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener { view, year, month, day ->
                var date: String = "${year}-${month+1}-${day}"
                presenter.getDayCalentarData(date)
            }
        }


    }


    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showDayCalentarData(calentarDayBean: CalentarDayBean<CalentarDayResult<CalentarDayData>>) {
        datePicker.visibility=View.GONE
        titleTextView.visibility = View.VISIBLE
        selectButton.visibility = View.VISIBLE
        contentTextView.visibility = View.VISIBLE
        titleTextView.text=calentarDayBean.result.data.date
        contentTextView.text = calentarDayBean.result.data.toString()
    }

    override fun showError(errorMsg: String) {
        toast(errorMsg)
    }
}
