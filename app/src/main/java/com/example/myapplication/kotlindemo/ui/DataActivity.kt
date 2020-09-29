package com.example.myapplication.kotlindemo.ui

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.adapter.SQLDataAdapter
import com.example.myapplication.kotlindemo.logic.modle.Book
import com.example.myapplication.kotlindemo.util.MyDateBaseHelper
import com.example.myapplication.kotlindemo.util.cvOf
import com.example.myapplication.kotlindemo.util.open
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_broadcast.*
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_item_view.*
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.NullPointerException

class DataActivity : BaseActivity() {

    private var bookList = ArrayList<Book>()

    private lateinit var sqlDataAdapter: SQLDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        ActivityCollector.addActivity(this)
        //获取数据库读取实例
        val dbHelper = MyDateBaseHelper(this,"BookStore.db",1)
        sqlDataAdapter = SQLDataAdapter(bookList)
        rv_sql_data.layoutManager = LinearLayoutManager(this)
        rv_sql_data.adapter = sqlDataAdapter
        btn_create.setOnClickListener {
            dbHelper.writableDatabase
        }

        btn_insert.setOnClickListener {
            val db = dbHelper.writableDatabase
            //此写法甚是牛逼
            val value1 = cvOf("name" to "MyBatis","author" to "Liu Zeng Hui","pages" to 345,"price" to 58.9)
            db.insert("Book",null,value1)
            //google KTX支持
            val value2 = contentValuesOf("name" to "Jobs","author" to "Zyique","pages" to 845,"price" to 98.9)
            val isOK = db.insert("Book", null, value2)
            "insert data result is $isOK".showToast()
        }

        btn_select.setOnClickListener {
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("select * from Book",null)
            bookList.clear()
            while (cursor.moveToNext()){
                //使用这种写法直观且合适 以前使用getIndex的方式比较啰嗦建议这样使用
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("author"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
                val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                bookList.add(Book(name, author, pages, price))
            }
            showMessage()
            cursor.close()
        }

        btn_delete.setOnClickListener {
            val db = dbHelper.writableDatabase
            val isOK = db.delete("Book","pages < ?", arrayOf("300"))
            "delete data result is $isOK".showToast()
        }

        btn_update.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("price",99)
            }
            val isOK = db.update("Book",value,"name = ?" , arrayOf("Java"))
            "update data result is $isOK".showToast()
        }
        btn_replace.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()    //开启事务
            try {
                db.delete("Book", null,null)
//                if (true){
//                    //手动抛异常让事务失败
//                    throw NullPointerException()
//                }
                val values = ContentValues().apply {
                    put("name","Game og Thrones")
                    put("author","George Martin")
                    put("pages",720)
                    put("price",20.85)
                }
                db.insert("Book",null, values)
                db.setTransactionSuccessful()      //事务执行成功
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                db.endTransaction()     //结束事务
            }
        }
    }

    fun spWrite(){
        getSharedPreferences("data",Context.MODE_PRIVATE).open {
            putString("name","zyique")
        }
    }

    fun spRead(){
        val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
        val name = prefs.getString("name","")
        tv_data_show.text = name
    }

    //文件存储
    fun save(text: String){
        try {
            val output = openFileOutput("data",Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            //use 函数保证流操作完成后自动关闭流
            writer.use {
                it.write(text)
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun showMessage(){
        runOnUiThread {
            sqlDataAdapter.notifyDataSetChanged()
        }
    }
}
