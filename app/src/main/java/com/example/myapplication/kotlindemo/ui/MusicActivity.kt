package com.example.myapplication.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import kotlinx.android.synthetic.main.activity_music.*
import kotlinx.android.synthetic.main.rv_item_view.*

class MusicActivity : BaseActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        ActivityCollector.addActivity(this)
        initMediaPlayer()
        btn_music_play.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }
        btn_music_pause.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }
        btn_music_stop.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
            }
        }
    }

    private fun initMediaPlayer(){
        val asstManager = assets
        val fd = asstManager.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
    companion object{
        fun actionStart(context: Context){
            val intent = Intent(context, MusicActivity::class.java)
            context.startActivity(intent)
        }
    }
}