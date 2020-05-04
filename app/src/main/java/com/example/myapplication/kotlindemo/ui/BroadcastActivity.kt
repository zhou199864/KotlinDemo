package com.example.myapplication.kotlindemo.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_broadcast.*

class BroadcastActivity : BaseActivity() {

    private lateinit var timeChangeReceiver: TimeChangeReceiver

    private val intentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        ActivityCollector.addActivity(this)
        intentFilter.addAction("com.example.myapplication.kotlindemo.ui.BroadcastActivity.TimeChangeReceiver")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
        btn_broadcast.setOnClickListener {
            sendBroadcast(Intent("com.example.myapplication.kotlindemo.ui.BroadcastActivity.TimeChangeReceiver"))
        }
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            "TimeChangeReceiver".showToast()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}
