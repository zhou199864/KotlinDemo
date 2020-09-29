package com.example.myapplication.kotlindemo.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import kotlinx.android.synthetic.main.activity_media.*

class MediaActivity : BaseActivity() {

    private val essay = "The snowman is moving! James invites him in. " +
            "The snowman has never been inside a house. He says hello to the cat. " +
            "He plays with paper towels.A moment later, the snowman takes James's hand and goes out." +
            "They go up, up, up into the air! They are flying!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)
        ActivityCollector.addActivity(this)
        //获得通知实例
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this,KotlinActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        //判断安卓版本是否大于 O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //大于O需要加入一个通知channel
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_HIGH)
            //未成功暂时不明白原因
//            val channel2 = NotificationChannel("important","Important",NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        btn_notify.setOnClickListener {
            noticeTest(manager, pi)
        }
        btn_camera_album.setOnClickListener {
            CameraAlbumActivity.actionStart(this)
        }
        btn_music.setOnClickListener {
            MusicActivity.actionStart(this)
        }
    }

    private fun noticeTest(manager: NotificationManager, pi: PendingIntent){
        val notification = NotificationCompat.Builder(this,"normal")
            .setContentTitle("This is content title")
            .setContentText(essay)
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon))
            .setContentIntent(pi)
            .setAutoCancel(true)
                //此属性设置通知内容全部显示不使用省略号   显示大图同理
//            .setStyle(NotificationCompat.BigTextStyle().bigText(essay))
            .build()
        manager.notify(1,notification)
    }

}
