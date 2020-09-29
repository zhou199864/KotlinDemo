package com.example.myapplication.kotlindemo

import android.app.Activity
/**
* @Author Zyique Zhou
* @Date 6/30/2020
* @Description control all activity eg:finish add etc.
*/
object ActivityCollector {
    private val activityList = ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activityList.add(activity)
    }

    fun removeActivity(activity: Activity){
        activityList.remove(activity)
    }

    fun finishAllActivity(){
        for (activity in activityList){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activityList.clear()
    }

}