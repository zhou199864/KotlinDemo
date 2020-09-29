package com.example.myapplication.kotlindemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.kotlindemo.logic.modle.User

class KotlinViewModel() : ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData){
        "${it.userName}"
    }
    val counter: LiveData<Int>
    get() = _counter
    private val _counter = MutableLiveData<Int>()
    init {
        _counter.value = 0
    }

    fun plusOne(){
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clean(){
        _counter.value = 0
    }

}