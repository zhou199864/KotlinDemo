package com.example.myapplication.kotlindemo.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.service.MyIntentService
import com.example.myapplication.kotlindemo.service.MyService
import kotlinx.android.synthetic.main.activity_service.*
import kotlinx.android.synthetic.main.rv_item_view.*
import kotlin.concurrent.thread

class ServiceActivity : BaseActivity() {

    private lateinit var downloadBinder: MyService.DownloadBinder

    private val TAG = "ServiceActivity"

    private val connection = object : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

    }
    
    val handle = object : Handler(){
        override fun handleMessage(msg: Message) {
            when (msg.what){
                // TODO: 7/14/2020
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        ActivityCollector.addActivity(this)
        btn_start_service.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            startService(intent)
        }
        btn_stop_service.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            stopService(intent)
        }
        btn_bind_service.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE)
        }
        btn_unbind_service.setOnClickListener {
            unbindService(connection)
        }
        btn_start_intent_service.setOnClickListener {
            Log.d(TAG, "Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this,MyIntentService::class.java)
            startService(intent)
        }
    }
}
