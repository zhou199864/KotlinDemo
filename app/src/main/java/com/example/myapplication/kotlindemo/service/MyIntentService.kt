package com.example.myapplication.kotlindemo.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService(name: String = "MyIntentService") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService", "Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService", "onDestroy: executed")
    }
}