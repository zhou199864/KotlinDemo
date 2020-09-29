package com.example.myapplication.kotlindemo.util

import okhttp3.OkHttpClient
import okhttp3.Request

object OkHttpUtil {

    fun getRequest(json: Any, address: String, callback: okhttp3.Callback){
        val client = OkHttpClient()
        val request = Request.Builder()
    }
}