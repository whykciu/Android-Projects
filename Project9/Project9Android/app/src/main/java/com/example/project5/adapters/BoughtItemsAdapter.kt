package com.example.project5.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project5.R

class BoughtItemsAdapter(private var boughtItemsNames: Array<String>, private var boughtItemsQuantities: Array<String>): RecyclerView.Adapter<BoughtItemsAdapter.BoughtItemsViewHolder>() {

    class BoughtItemsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvProductName: TextView = view.findViewById(R.id.tvBoughtProductName)
        val tvCost: TextView = view.findViewById(R.id.tvOverallCost)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoughtItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bought_products_item, parent, false)
        return BoughtItemsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boughtItemsNames.size
    }

    override fun onBindViewHolder(holder: BoughtItemsViewHolder, position: Int) {
        val boughtItemName = boughtItemsNames[position]
        val boughtItemQuantity = boughtItemsQuantities[position]

        holder.tvProductName.text = boughtItemName
        holder.tvCost.text = "x $boughtItemQuantity"

    }

}
