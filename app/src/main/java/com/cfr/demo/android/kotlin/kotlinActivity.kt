package com.cfr.demo.android.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cfr.demo.android.R
import kotlinx.android.synthetic.main.activity_kotlin2.*

class kotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin2)
      btn.setOnClickListener{
          var intent = Intent()
          intent.setClass(this,kotlinMainActivity::class.java)
          startActivity(intent)
      }
    }
}
