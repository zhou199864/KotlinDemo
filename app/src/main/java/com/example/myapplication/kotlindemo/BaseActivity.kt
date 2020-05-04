package com.example.myapplication.kotlindemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
/**
* @Author Zyique Zhou
* @Date 4/25/2020
* @Description 基准activity
*/
open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity",javaClass.simpleName);
    }
}