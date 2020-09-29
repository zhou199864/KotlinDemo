package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.adapter.ListViewAdapter
import com.example.myapplication.kotlindemo.logic.modle.Fruit
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : BaseActivity() {

    private var list = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        ActivityCollector.addActivity(this)
        repeat(10){
            list.add(Fruit("aa",R.drawable.apple_pic))
            list.add(Fruit("bb",R.drawable.banana_pic))
            list.add(Fruit("cc",R.drawable.orange_pic))
        }
        lv.adapter = ListViewAdapter(list)
        //若参数没有使用到那么可以用 _ 代替
        lv.setOnItemClickListener { _, _, _, id ->
            "click$id".showToast()
        }
    }
}
