package com.example.myapplication.kotlindemo.logic.network

import com.example.myapplication.kotlindemo.logic.modle.Json
import com.example.myapplication.kotlindemo.logic.modle.PlaceResponse
import com.example.myapplication.kotlindemo.logic.modle.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("user/selectUser")
    fun getUserData(@Body json: Json): Call<User>

    @GET("v2/place?token=DeA0A3ubHmYzw0UZ&lang=zh_CN&query=北京")
    fun getJsonData(): Call<PlaceResponse>
}