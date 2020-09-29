package com.example.myapplication.kotlindemo.util

import android.os.AsyncTask

class DownloadTask: AsyncTask<Unit,Int,Boolean>() {

    /**
     * 此方法中的所有代码都会在子线程中运行我们应该在这里处理耗时操作.
     * 任务一旦完成,就可以通过return语句将任务执行的结果返回,如果AsyncTask中第三个参数传入Unit则不需要返回.
     * 此方法不允许进行UI操作
     */
    override fun doInBackground(vararg params: Unit?): Boolean {
        TODO("Not yet implemented")
    }
    //在后台开始执行之前调用用于进行界面上的初始化操作
    override fun onPreExecute() {
        super.onPreExecute()
    }

    /**
     * 当后台任务调用publishProgress(Progress...)方法后 此方法很快就会被调用 可
     * 以在这里进行UI操作 eg:进度更新
     */
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    /**
     * 当后台任务执行完毕并return返回后此方法很快就被调用.
     * 返回的数据会作为参数传递到此方法中.
     * 可以再次进行一些UI操作 eg:提醒结果
     */
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }
}