package com.example.myapplication.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.logic.modle.ChatMsg

//chat recyclerview 适配器
class ChatAdapter(private val msgList: List<ChatMsg>) : RecyclerView.Adapter<MsgViewHolder>() {
    //在此处进行类型判断是 接收还是发送 写法可能不是很好理解需适应
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == ChatMsg.TYPE_RECEIVER){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
        LeftViewHolder(view)
    }else{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
        RightViewHolder(view)
    }
    //return type 与onCreateViewHolder方法中的viewType参数对应默认不覆写此方法需要手动覆写如果不复写那么类型只会一种
    override fun getItemViewType(position: Int): Int = msgList[position].type
    //列表长度
    override fun getItemCount(): Int = msgList.size
    //绑定试图
    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder){
            is LeftViewHolder -> holder.leftMsg.text = msg.msg
            is RightViewHolder -> holder.rightMsg.text = msg.msg
        }
    }
}