package com.example.project5.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project5.models.Product
import com.example.project5.R
import com.example.project5.models.SharedViewModel


class BagListAdapter(private val sharedViewModel: SharedViewModel, private var bagProducts: List<Map.Entry<Product, Int>>) : RecyclerView.Adapter<BagListAdapter.BagListViewHolder>(){

    class BagListViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val nameTv: TextView = view.findViewById(R.id.tvBagProductName)
        val quantityTv: TextView = view.findViewById(R.id.tvBagQuantity)
        val productIv: ImageView = view.findViewById(R.id.ivProductBag)
        val plusButton: Button = view.findViewById(R.id.btnBagPlus)
        val minusButton: Button = view.findViewById(R.id.btnBagMinus)
        val deleteButton: ImageButton = view.findViewById(R.id.iBtnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bag_list_item, parent, false)
        return BagListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bagProducts.size
    }

    override fun onBindViewHolder(holder: BagListViewHolder, position: Int) {

        val entry = bagProducts[position]

        holder.nameTv.text = entry.key.name
        holder.quantityTv.text = entry.value.toString()
        holder.productIv.setImageResource(entry.key.img)

        holder.plusButton.setOnClickListener{
            sharedViewModel.increaseQuantityInBag(holder.adapterPosition)
        }

        holder.minusButton.setOnClickListener{
            sharedViewModel.decreaseQuantityInBag(holder.adapterPosition)
        }

        holder.deleteButton.setOnClickListener {
            sharedViewModel.deleteFromBag(holder.adapterPosition)
        }

    }

    fun updateData(newData: List<Map.Entry<Product, Int>>){
        bagProducts = newData
        Log.d("BagListAdapter", "updated Bag data")
        notifyDataSetChanged()
    }

}