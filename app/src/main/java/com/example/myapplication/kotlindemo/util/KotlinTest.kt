package com.example.myapplication.kotlindemo.util

import kotlinx.coroutines.*

fun main() {
//    runBlocking {
//        launch {
//            println("launch1")
//            delay(1000)
//            println("launch1 finished")
//        }
//        launch {
//            println("launch2")
//            delay(1000)
//            println("launch2 finished")
//        }
//    }
//    Thread.sleep(1000)
    val start = System.currentTimeMillis()
    runBlocking {
        repeat(100000){
            launch {
                println(".")
            }
        }
    }
    val end = System.currentTimeMillis()
    println("time is ${end - start}")
}

suspend fun printDot() = coroutineScope{
    launch {
        println(".")
        delay(1000)
    }
}