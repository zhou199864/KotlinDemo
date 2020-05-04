package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R

class DataActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        ActivityCollector.addActivity(this)
    }
}
