package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R

class FragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        ActivityCollector.addActivity(this)
    }
}
