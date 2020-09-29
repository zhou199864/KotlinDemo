package com.example.myapplication.kotlindemo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.BaseActivity
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.util.Later
import com.example.myapplication.kotlindemo.util.later
import com.example.myapplication.kotlindemo.util.showToast
import kotlinx.android.synthetic.main.activity_content_provider.*
import java.lang.Exception

class ContentProviderActivity : BaseActivity() {

    private val contactList = ArrayList<String>()

    private lateinit var adapter: ArrayAdapter<String>

    val p by later{
        "later test".showToast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        ActivityCollector.addActivity(this)
        btn_apply.setOnClickListener {
            //延迟加载调用
//            p
            adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList)
            lv_contact.adapter = adapter
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
            } else {
                readContact()
            }

            //拨打电话
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
//            } else {
//                call()
//            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readContact(){
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while (moveToNext()){
                val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactList.add("$displayName\n$number")
            }
            adapterNotify()
            close()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContact()
//                    call()
                } else {
                    "You denied the permission".showToast()
                }
            }
        }
    }

    private fun adapterNotify(){
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

}
