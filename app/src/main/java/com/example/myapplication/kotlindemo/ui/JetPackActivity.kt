package com.example.myapplication.kotlindemo.ui

import android.os.Bundle
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.lifecycles.MyObserver
import com.example.myapplication.kotlindemo.logic.modle.Book
import com.example.myapplication.kotlindemo.util.AppDataBase
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_jet_pack.*
import kotlin.concurrent.thread

class JetPackActivity : BaseActivity() {

    val bookDao = AppDataBase.getDatabase().bookDao()
    val book1 = Book("Java","LXH",789,78.9)
    val book2 = Book("Android","GL",689,88.9)
    val lists = listOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack)
        ActivityCollector.addActivity(this)
        lifecycle.addObserver(MyObserver())
        btn_room_insert.setOnClickListener {
            thread {
                book1.id = bookDao.insertBook(book1)
                book2.id = bookDao.insertBook(book2)
            }
        }
        btn_room_delete.setOnClickListener {
            thread {
                bookDao.deleteBook("Android")
            }
        }
        btn_room_update.setOnClickListener {
            thread {
//                bookDao.updateBook(109.9,"Android")
                book2.price = 79.8
                val id = bookDao.updateBook(book2)
                println(id)
            }
        }
        btn_room_select.setOnClickListener {
            thread {
                val lists = bookDao.loadAllBook()
                for (book in lists){
                    println(book)
                }
            }
        }
    }
}
