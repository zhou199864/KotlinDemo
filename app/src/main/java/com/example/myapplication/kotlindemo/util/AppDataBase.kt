package com.example.myapplication.kotlindemo.util

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.kotlindemo.MyApplication
import com.example.myapplication.kotlindemo.logic.dao.BookDao
import com.example.myapplication.kotlindemo.logic.modle.Book

@Database(version = 1, entities = [Book::class])
abstract class AppDataBase : RoomDatabase(){
    abstract fun bookDao() : BookDao

    companion object{

        private var instance: AppDataBase? = null

        @Synchronized
        fun getDatabase() : AppDataBase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(MyApplication.context,AppDataBase::class.java,"app_database").build().apply {
                instance = this
            }
        }
    }

}