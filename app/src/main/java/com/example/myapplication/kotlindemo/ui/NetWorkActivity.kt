package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.logic.modle.Json
import com.example.myapplication.kotlindemo.logic.modle.User
import com.example.myapplication.kotlindemo.logic.network.UserService
import com.example.myapplication.kotlindemo.util.ServiceCreator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_net_work.*
import okhttp3.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetWorkActivity : BaseActivity() {

//    private val JSON = "application/json; charset=utf-8".toMediaType()

    private val jsonArray = "[{\"name\":\"Tom\",\"age\":20},{\"name\":\"Zyique\",\"age\":22},{\"name\":\"Abit\",\"age\":23}]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work)
        ActivityCollector.addActivity(this)
        btn_web_view.setOnClickListener {
            WebViewActivity.actionStart(this)
        }
        btn_get.setOnClickListener {
//            httpURLConnectionTest()
//            okHttpPOSTTest()
//            okHttpGetTest()
//            jsonArrayAnalyze()
            retrofitGETTest()
        }
    }
    //解析json进bean数组
    private fun jsonArrayAnalyze(){
        val typeOf = object : TypeToken<List<Person>>(){}.type
        val people = Gson().fromJson<List<Person>>(jsonArray,typeOf)
        for (p in people){
            println(p)
        }
    }

    private fun retrofitPOSTTest(){
        val userService = ServiceCreator.create(UserService::class.java)
        userService.getUserData(Json(1)).enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                println(response.body())
            }
        })
    }

    private fun retrofitGETTest(){
        val userService = ServiceCreator.create(UserService::class.java)
        val data = userService.getJsonData()
    }

//    private fun okHttpGetTest(){
//        thread {
//            try {
//                val client = OkHttpClient()
//                val request = Request.Builder()
//                    .url("http://47.103.135.104:8099/user/jsonTest")
//                    .build()
//                val response = client.newCall(request).execute()
//                val responseData = response.body?.string()
//                if (responseData != null){
//                    showMessage(responseData)
//                }
//            } catch (e: Exception){
//                e.printStackTrace()
//            }
//        }
//    }

//    private fun okHttpPOSTTest(){
//        thread {
//            try {
//                val requestBody = "{\"id\":3}".toRequestBody(JSON)
//                val client = OkHttpClient()
//                val request = Request.Builder()
//                    .post(requestBody)
//                    .url("http://47.103.135.104:8099/user/selectUser")
//                    .build()
//                val response = client.newCall(request).execute()
//                val responseData = response.body?.string()
//                if (responseData != null){
//                    val person = Gson().fromJson(responseData,User::class.java)
//                    println(person)
//                    showMessage(responseData)
//                }
//            } catch (e: Exception){
//                e.printStackTrace()
//            }
//        }
//    }

    private fun httpURLConnectionTest(){
        thread {
            var connection: HttpURLConnection? = null
            try {
                //获取实例
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                val response = StringBuilder()
                //指定请求类型 post见书433
                connection.requestMethod = "GET"
                //超时时间
                connection.connectTimeout = 8000
                //读取超时
                connection.readTimeout = 8000
                val input = connection.inputStream
                val read = BufferedReader(InputStreamReader(input))
                read.use {
                    read.forEachLine {
                        response.append(it)
                    }
                }
                showMessage(response.toString())
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }

        }
    }

    private fun showMessage(string: String){
        runOnUiThread {
            tv_network.text = string
        }
    }

    data class Person(val name: String,val age: Int)
}