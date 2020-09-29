package com.example.myapplication.kotlindemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_offline.setOnClickListener {
            val intent = Intent("com.example.myapplication.FORCE_OFFLINE")
            "send offline broadcast".showToast()
            sendBroadcast(intent)
        }
    }
}