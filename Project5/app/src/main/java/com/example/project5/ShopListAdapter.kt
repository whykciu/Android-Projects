package com.example.project5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ShopListAdapter(private val sharedViewModel: SharedViewModel, private val context: Context?, private val fragment: Fragment) : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    class ShopListViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val nameTv: TextView = view.findViewById(R.id.tvProductName)
        val quantityTv: TextView = view.findViewById(R.id.tvQuantity)
        val productIv: ImageView = view.findViewById(R.id.ivProductShop)
        val plusButton: Button = view.findViewById(R.id.btnPlus)
        val minusButton: Button = view.findViewById(R.id.btnMinus)
        val addButton: ImageButton = view.findViewById(R.id.iBtnAdd)
        val cardView: CardView = view.findViewById(R.id.cvShopItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)
        return ShopListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sharedViewModel.getAllProducts().value!!.size
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {

        val product: Product = sharedViewModel.getAllProducts().value!![position]

        holder.cardView.setOnClickListener{
            val intent: Intent = Intent(context, ProductDescriptionActivity::class.java)
            intent.putExtra("NAME", product.name)
            intent.putExtra("DESCRIPTION", product.description)
            context?.startActivity(intent)
        }

        holder.nameTv.text = product.name
        holder.productIv.setImageResource(R.drawable.burger)

        sharedViewModel.getAllProducts().observe(fragment.viewLifecycleOwner) { list ->
            holder.quantityTv.text = list[position].quantity.toString()
        }

        holder.quantityTv.text = product.quantity.toString()

        holder.plusButton.setOnClickListener{
            sharedViewModel.increaseQuantity(position)
        }

        holder.minusButton.setOnClickListener{
            sharedViewModel.decreaseQuantity(position)
        }

        holder.addButton.setOnClickListener{
            sharedViewModel.addToBag(position)
        }

    }


}