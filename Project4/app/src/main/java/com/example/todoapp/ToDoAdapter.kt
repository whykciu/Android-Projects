package com.example.todoapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class ToDoAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var itemTextView = view.findViewById<TextView>(R.id.tvItem)
        var descTextView = view.findViewById<TextView>(R.id.tvDesc)
        var dateTextView = view.findViewById<TextView>(R.id.tvDate)
        var checkBox = view.findViewById<CheckBox>(R.id.cbItem)

        fun bind(item: Item){
            itemTextView.text = item.text
            descTextView.text = item.desc
            dateTextView.text = SimpleDateFormat("dd/MM/yyyy").format(item.date)
            checkBox.isChecked = item.status
            strikeThroughItem(checkBox.isChecked)

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                strikeThroughItem(isChecked)
            }
        }

        fun TextView.showStrikeThrough(status: Boolean) {
            paintFlags =
                if (status) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        fun strikeThroughItem(isChecked: Boolean){
            itemTextView.showStrikeThrough(isChecked)
            descTextView.showStrikeThrough(isChecked)
            dateTextView.showStrikeThrough(isChecked)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ToDoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.bind(itemList[position])

    }




}