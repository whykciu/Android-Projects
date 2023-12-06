package com.example.project5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class BagListAdapter(private val sharedViewModel: SharedViewModel, private val fragment: Fragment) : RecyclerView.Adapter<BagListAdapter.BagListViewHolder>(){

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
        return sharedViewModel.getBagProducts().value!!.size
    }

    override fun onBindViewHolder(holder: BagListViewHolder, position: Int) {

        val entry = sharedViewModel.getBagProducts().value!![position]

        holder.nameTv.text = entry.key.name
        holder.productIv.setImageResource(R.drawable.burger)

        sharedViewModel.getBagProducts().observe(fragment.viewLifecycleOwner) { bagList ->
            holder.quantityTv.text = bagList[position].value.toString()
        }

        holder.plusButton.setOnClickListener{

        }

        holder.minusButton.setOnClickListener{

        }

        holder.deleteButton.setOnClickListener {

        }


    }

}