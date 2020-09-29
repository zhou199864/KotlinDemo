package com.example.myapplication.kotlindemo.logic.modle

//recyclerview聊天视图的javaBean
data class ChatMsg(val msg: String,val type: Int){
    //单列可直接访问类使用 static final etc.
    companion object{
        const val TYPE_RECEIVER = 0
        const val TYPE_SENT = 1
    }
}