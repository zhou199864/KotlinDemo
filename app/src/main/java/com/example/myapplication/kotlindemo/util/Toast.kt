package com.example.myapplication.kotlindemo.util

import android.widget.Toast
import com.example.myapplication.kotlindemo.MyApplication

fun String.showToast(){
    Toast.makeText(MyApplication.context,this, Toast.LENGTH_SHORT).show()
}