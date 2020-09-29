package com.example.myapplication.kotlindemo.util

import kotlin.reflect.KProperty
//顶层函数
fun <T> later(block: () -> T) = Later(block)

class Later<T>(val block:() -> T){
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T{
        if (value == null){
            value = block()
        }
        return value as T
    }
}