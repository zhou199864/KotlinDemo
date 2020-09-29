package com.example.myapplication.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.util.showToast
import com.example.myapplication.kotlindemo.viewmodel.KotlinViewModel
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : BaseActivity() {

    lateinit var viewModel: KotlinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        ActivityCollector.addActivity(this)
        intent.extras?.getString("data")?.showToast()
        //of方法被废弃
        viewModel = ViewModelProvider(this).get(KotlinViewModel::class.java)
        btn_plus.setOnClickListener {
            viewModel.plusOne()
            refresh()
        }
        viewModel.counter.observe(this, Observer {
            tv_result.text = it.toString()
        })
    }

    private fun refresh(){

    }

    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, KotlinActivity::class.java).apply {
                putExtra("data", data)
            }
            context.startActivity(intent)
        }
    }
}
