package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.adapter.ChatAdapter
import com.example.myapplication.kotlindemo.logic.modle.ChatMsg
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.ArrayList

class RecyclerViewActivity : BaseActivity() {

    private var msgList = ArrayList<ChatMsg>()

    private lateinit var data: String

    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        ActivityCollector.addActivity(this)
        initMsg()
        //判空处理 在此处去掉影响不大
        if (!::adapter.isInitialized) {
            adapter = ChatAdapter(msgList)
        }
        rv_chat.layoutManager = LinearLayoutManager(this)
        rv_chat.adapter = adapter
        btn_sent.setOnClickListener {
            data = ed_message.text.toString().trim()
            if (!TextUtils.isEmpty(data)) {
                msgList.add(ChatMsg(data, ChatMsg.TYPE_SENT))
                //通知更新
                adapter?.notifyItemChanged(msgList.size - 1)
                //定位到最后一
                rv_chat.scrollToPosition(msgList.size - 1)
                ed_message.setText("")
            }
        }
    }

    private fun initMsg() {
        msgList.add(ChatMsg("Hello", ChatMsg.TYPE_RECEIVER))
        msgList.add(ChatMsg("World", ChatMsg.TYPE_SENT))
        msgList.add(ChatMsg("It's me", ChatMsg.TYPE_RECEIVER))
    }
}
