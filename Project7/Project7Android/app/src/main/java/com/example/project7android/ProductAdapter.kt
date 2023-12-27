package com.example.project7android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(val productList: List<ProductItem>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>(){
    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tvProductItemName)
        var tvCategoryId: TextView = itemView.findViewById(R.id.tvProductItemCategory)
        var tvPrice: TextView = itemView.findViewById(R.id.tvProductItemPrice)

        fun bind(product: ProductItem){
            tvName.text = product.name
            tvCategoryId.text = tvCategoryId.text.toString() + product.categoryId.toString()
            tvPrice.text = tvPrice.text.toString() + product.price.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position])
    }
}