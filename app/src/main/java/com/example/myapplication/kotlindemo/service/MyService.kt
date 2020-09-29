package com.example.myapplication.kotlindemo.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.ui.ServiceActivity
import kotlin.concurrent.thread

class MyService : Service() {
    private val TAG = "MyService"
    private val mBinder = DownloadBinder()
    //在service创建时调用
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        //构建一个前台service
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("my_service","前台service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this,ServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        val notification = NotificationCompat.Builder(this,"my_service")
            .setContentTitle("This is title")
            .setContentText("this is content to show how to use service")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon))
            .build()
        startForeground(1,notification)
    }
    //每次service启动时调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        //尽量避免ANR在子线程中进行操作
        thread {
            //具体逻辑编写
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }
    //销毁service时调用
    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
    class DownloadBinder : Binder(){
        fun startDownload(){
            Log.d("MyService", "startDownload: ")
        }
        fun getProgress(): Int{
            Log.d("MyService", "getProgress: ")
            return 0
        }
    }
}
