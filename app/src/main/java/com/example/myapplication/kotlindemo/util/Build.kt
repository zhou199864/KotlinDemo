package com.example.myapplication.kotlindemo.util

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}