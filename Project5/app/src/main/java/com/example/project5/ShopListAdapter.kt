package com.example.project5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ShopListAdapter(private val productList: List<Product>, private val context: Context?) : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    class ShopListViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val nameTv: TextView = view.findViewById(R.id.tvProductName)
        val quantityTv: TextView = view.findViewById(R.id.tvQuantity)
        val productIv: ImageView = view.findViewById(R.id.ivProductShop)
        val plusButton: Button = view.findViewById(R.id.btnPlus)
        val minusButton: Button = view.findViewById(R.id.btnMinus)

        fun bind(product: Product){

            nameTv.text = product.name
            productIv.setImageResource(R.drawable.burger)

            plusButton.setOnClickListener{
                val quantity: Int = quantityTv.text.toString().toInt() + 1
                if(quantity <= product.stockLevel){
                    quantityTv.text = quantity.toString()
                }
            }

            minusButton.setOnClickListener{
                val quantity: Int = quantityTv.text.toString().toInt() - 1
                if(quantity >= 0){
                    quantityTv.text = quantity.toString()
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)
        return ShopListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {

        val view: View = holder.itemView
        val cardView: CardView = view.findViewById(R.id.cvShopItem)
        val product: Product = productList[position]

        cardView.setOnClickListener{
            val intent: Intent = Intent(context, ProductDescriptionActivity::class.java)
            intent.putExtra("NAME", product.name)
            intent.putExtra("DESCRIPTION", product.description)
            context?.startActivity(intent)
        }

        holder.bind(product)

    }


}