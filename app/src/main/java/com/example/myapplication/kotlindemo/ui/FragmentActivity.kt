package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import kotlinx.android.synthetic.main.left_fragment.*

//simple news demo
class FragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        ActivityCollector.addActivity(this)
    }
}
