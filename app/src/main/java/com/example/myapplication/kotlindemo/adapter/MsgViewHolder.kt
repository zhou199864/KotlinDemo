package com.example.myapplication.kotlindemo.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.kotlindemo.R

//使用密封类进行多种类型的适配器编写
sealed class MsgViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

class LeftViewHolder(itemView: View): MsgViewHolder(itemView){
    val leftMsg: TextView = itemView.findViewById(R.id.tv_msg_left)
}

class RightViewHolder(itemView: View): MsgViewHolder(itemView){
    val rightMsg: TextView = itemView.findViewById(R.id.tv_msg_right)
}
