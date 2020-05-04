package com.example.myapplication.kotlindemo

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.kotlindemo.adapter.ActivityAdapter
import com.example.myapplication.kotlindemo.ui.*
import com.example.myapplication.kotlindemo.util.StaticVariable
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
/**
* @Author Zyique Zhou
* @Date 4/24/2020
* @Description learned kotlin
*/
class MainActivity : BaseActivity(){

    private val list = ArrayList<String>()

    private val activityMap = HashMap<Int,String>()

    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCollector.addActivity(this)
        addActivity()
        activityAdapter = ActivityAdapter(activityMap){ pos ->
            intent = null
            when (activityMap[pos]){
                StaticVariable.kotlinActivity -> intent = Intent(this,KotlinActivity::class.java)
                StaticVariable.ListViewActivity -> intent = Intent(this,ListViewActivity::class.java)
                StaticVariable.RecyclerViewActivity -> intent = Intent(this,RecyclerViewActivity::class.java)
                StaticVariable.FragmentActivity -> intent = Intent(this,FragmentActivity::class.java)
                StaticVariable.BroadcastActivity -> intent = Intent(this,BroadcastActivity::class.java)
                StaticVariable.DataActivity -> intent = Intent(this,DataActivity::class.java)
                StaticVariable.ContentProviderActivity -> intent = Intent(this,ContentProviderActivity::class.java)
                StaticVariable.ServiceActivity -> intent = Intent(this,ServiceActivity::class.java)
                StaticVariable.MediaActivity -> intent = Intent(this,MediaActivity::class.java)
                StaticVariable.MaterialDesignActivity -> intent = Intent(this,MaterialDesignActivity::class.java)
                StaticVariable.JetPackActivity -> intent = Intent(this,JetPackActivity::class.java)
            }
            "Jump to ${activityMap[pos]}".showToast()
            if (intent != null){
                startActivity(intent)
            }else{
                "No Activity has been select".showToast()
            }
        }
        //判空写法
        if (!::activityAdapter.isInitialized) {
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = activityAdapter
        }
    }

    //添加Activity
    private fun addActivity(){
        activityMap[0] = StaticVariable.kotlinActivity
        activityMap[1] = StaticVariable.ListViewActivity
        activityMap[2] = StaticVariable.RecyclerViewActivity
        activityMap[3] = StaticVariable.FragmentActivity
        activityMap[4] = StaticVariable.BroadcastActivity
        activityMap[5] = StaticVariable.DataActivity
        activityMap[6] = StaticVariable.ContentProviderActivity
        activityMap[7] = StaticVariable.ServiceActivity
        activityMap[8] = StaticVariable.MediaActivity
        activityMap[9] = StaticVariable.MaterialDesignActivity
        activityMap[10] = StaticVariable.JetPackActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }


}
