package com.example.myapplication.kotlindemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//创建KotlinViewModel实例时传参
class KotlinViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KotlinViewModel() as T
    }
}