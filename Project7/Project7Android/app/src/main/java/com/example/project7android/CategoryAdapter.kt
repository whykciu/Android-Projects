package com.example.project7android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(val categoryList: List<CategoryItem>) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>(){
    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tvCategoryItem)

        fun bind(category: CategoryItem){
            tvName.text = category.name + " Id: " + category.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
    }
}