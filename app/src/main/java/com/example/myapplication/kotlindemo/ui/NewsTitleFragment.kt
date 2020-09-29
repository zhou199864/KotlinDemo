package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.logic.modle.News
import com.example.myapplication.kotlindemo.util.times
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.news_title_fragment.*

class NewsTitleFragment(): Fragment() {

    private var isTwoPage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //判断在activity是否存在一个名为contentLayout得布局进而判断是否使用双页
        isTwoPage = activity?.findViewById<View>(R.id.content_layout) != null
        val layoutManager = LinearLayoutManager(activity)
        rv_news_title_fragment.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        rv_news_title_fragment.adapter = adapter
    }

    inner class NewsAdapter(private val newsList: List<News>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

        inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val newsTitle: TextView = itemView.findViewById(R.id.tv_news_title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
            val holder = NewsViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if (isTwoPage){
                    val fragment = news_content_fragment as NewsContentFragment
                    fragment.refresh(news.title,news.content)
                } else {
                    NewsContentActivity.actionStart(parent.context,news.title,news.content)
                }
            }
            return holder
        }

        override fun getItemCount(): Int = newsList.size

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }
    }

    private fun getNews(): List<News>{
        val newsList = ArrayList<News>()
        for (i in 1..50){
            val news = News("this is news title $i",getRandomLengthString("this is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(string: String): String = string * (1..20).random()



}