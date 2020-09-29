package com.example.myapplication.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.kotlindemo.R
import com.example.myapplication.kotlindemo.logic.modle.Book

class SQLDataAdapter(private val dataList: List<Book>):
    RecyclerView.Adapter<SQLDataAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tv: TextView = itemView.findViewById(R.id.tv_sql_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sql_data_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val book = dataList[position]
        holder.tv.text = "name: ${book.name}\tauthor: ${book.author}\n" +
                "pages: ${book.pages}\tprice: ${book.price}"
    }


}