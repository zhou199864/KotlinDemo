package com.example.myapplication.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.logic.modle.Fruit


class ListViewAdapter(val list:List<Fruit>): BaseAdapter() {

    private lateinit var fruit: Fruit

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int = list.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val customView: View
        val viewHolder: ViewHolder
        if (convertView == null){
            customView = LayoutInflater.from(parent?.context).inflate(R.layout.lv_item,parent,false)
            val name: TextView = customView.findViewById(R.id.tv_item)
            val img: ImageView = customView.findViewById(R.id.img_item)
            viewHolder = ViewHolder(name,img)
            customView.tag = viewHolder
        }else{
            customView = convertView
            viewHolder = customView.tag as ViewHolder
        }
        fruit = getItem(position) as Fruit
        if (fruit != null){
            viewHolder.tv.text = fruit.name
            viewHolder.img.setImageResource(fruit.id)
        }
        return customView
    }
    inner class ViewHolder(val tv: TextView,val img: ImageView)
}
