package com.example.myapplication.kotlindemo.ui

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_broadcast.*

class BroadcastActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        ActivityCollector.addActivity(this)
        btn_enter.setOnClickListener {
            val userName = ed_user_name.text.toString().trim()
            val password = ed_password.text.toString().trim()
            if (userName == "admin" && password == "admin"){
                "Login Success".showToast()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                "UserName or Password has error please checked".showToast()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
