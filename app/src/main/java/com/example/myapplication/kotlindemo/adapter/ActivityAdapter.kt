package com.example.myapplication.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.kotlindemo.R
//高阶函数回调使用如下
class ActivityAdapter(private val activityList: Map<Int,String>, private val call: (Int) -> Unit) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = activityList.size;

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.text = activityList[position]
        holder.button.setOnClickListener {
            call(position)
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.btn)
    }

    interface Call{
        fun call(pos: Int)
    }
}
