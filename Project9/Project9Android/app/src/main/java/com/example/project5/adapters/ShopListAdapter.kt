package com.example.project5.adapters

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
import com.example.project5.models.Product
import com.example.project5.ProductDescriptionActivity
import com.example.project5.R
import com.example.project5.models.SharedViewModel

class ShopListAdapter(private val sharedViewModel: SharedViewModel, private val context: Context?, private val fragment: Fragment) : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    class ShopListViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val nameTv: TextView = view.findViewById(R.id.tvProductName)
        val quantityTv: TextView = view.findViewById(R.id.tvQuantity)
        val productIv: ImageView = view.findViewById(R.id.ivProductShop)
        val plusButton: Button = view.findViewById(R.id.btnPlus)
        val minusButton: Button = view.findViewById(R.id.btnMinus)
        val addButton: ImageButton = view.findViewById(R.id.iBtnAdd)
        val cardView: CardView = view.findViewById(R.id.cvShopItem)
        val overlay: TextView = view.findViewById(R.id.tvOverlayOutOfStock)

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
            val intent = Intent(context, ProductDescriptionActivity::class.java)
            intent.putExtra("NAME", product.name)
            intent.putExtra("DESCRIPTION", product.description)
            intent.putExtra("IMAGE_ID", product.img.toString())
            context?.startActivity(intent)
        }

        holder.nameTv.text = product.name
        holder.productIv.setImageResource(product.img)


        sharedViewModel.getAllProducts().observe(fragment.viewLifecycleOwner) { list ->

            if(list[position].quantity > list[position].stockLevel){
                list[position].quantity = 0
            }

            if(list[position].stockLevel <= 0){
                holder.overlay.visibility = View.VISIBLE
                holder.addButton.setBackgroundResource(R.drawable.button_out_of_stock)
            } else {
                holder.overlay.visibility = View.GONE
                holder.addButton.setBackgroundResource(R.drawable.button)
            }

            holder.quantityTv.text = list[position].quantity.toString()
        }

        holder.plusButton.setOnClickListener{
            sharedViewModel.increaseQuantity(holder.adapterPosition)
        }

        holder.minusButton.setOnClickListener{
            sharedViewModel.decreaseQuantity(holder.adapterPosition)
        }

        holder.addButton.setOnClickListener{
            if(holder.quantityTv.text.toString().toInt() > 0){
                sharedViewModel.addToBag(holder.adapterPosition)
            }
        }

    }


}