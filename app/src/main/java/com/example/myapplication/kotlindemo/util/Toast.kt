package com.example.myapplication.kotlindemo.util

import android.widget.Toast
import com.example.myapplication.kotlindemo.MyApplication

/**
* @Author Zyique Zhou
* @Date 6/24/2020
* @Description 
*/
fun String.showToast(){
    Toast.makeText(MyApplication.context,this, Toast.LENGTH_SHORT).show()
}