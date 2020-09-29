package com.example.myapplication.kotlindemo.logic.modle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var name: String, var author: String, var pages: Int, var price: Double){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}