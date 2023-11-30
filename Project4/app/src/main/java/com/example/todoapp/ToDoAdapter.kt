package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val itemTextView: TextView

        init{
            itemTextView = view.findViewById(R.id.tvItem)
        }

        fun bind(text: String){
            this.itemTextView.text = text
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

        holder.bind(itemList[position].text)

    }



}