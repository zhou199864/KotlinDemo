package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.kotlindemo.R
import kotlinx.android.synthetic.main.news_content_item.*

class NewsContentFragment(): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_item,container,false)
    }

    fun refresh(title: String, content: String){
        //此处布局应该在最外层使用嵌套如果不进行嵌套的话ke得插件无法进行识别进而会导致 NPE
        content_layout.visibility = View.VISIBLE
        tv_news_title.text = title
        tv_news_content.text = content
    }
    
}