package com.example.myapplication.kotlindemo.logic.dao

import androidx.room.*
import com.example.myapplication.kotlindemo.logic.modle.Book

@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book): Long

    @Query("update Book set price = :price where name = :bookName")
    fun updateBook(price: Double, bookName: String)

    //update 必须保持同一对象
    @Update
    fun updateBook(book: Book) : Int

    @Query("select * from Book")
    fun loadAllBook(): List<Book>

    @Query("select * from Book where name = :name")
    fun loadBookByName(name: String): Book

    @Query("delete from Book where name = :name")
    fun deleteBook(name: String)
}