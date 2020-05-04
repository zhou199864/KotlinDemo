package com.example.myapplication.kotlindemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R

class KotlinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        ActivityCollector.addActivity(this)
    }
}
