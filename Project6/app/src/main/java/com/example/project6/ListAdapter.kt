package com.example.project6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project6.modules.BagEntity
import com.example.project6.modules.User
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList

class ListAdapter(private val list: RealmResults<User>) : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    class ListHolder(view: View) : RecyclerView.ViewHolder(view){

        private val nameTv: TextView = view.findViewById(R.id.tvName)
        private val nickTv: TextView = view.findViewById(R.id.tvNick)
        private val productsTv: TextView = view.findViewById(R.id.tvProducts)
        private val sumTv: TextView = view.findViewById(R.id.tvSum)

        fun bind(user: User) {
            var sum: Double = 0.0
            val products: RealmList<BagEntity> = user.shoppingBag!!.products

            nameTv.text = user.name + " " + user.surname
            nickTv.text = user.nickname
            val sB = StringBuilder()
            for(entity in products){
                sB.append("• Category: " + entity.product!!.category!!.name  + " | " + entity.quantity.toString() + " x " + entity.product!!.name + " " + entity.product!!.price + "\n")
                sum += (entity.product!!.price * entity.quantity)
            }

            productsTv.text = sB.toString()
            sumTv.text = sumTv.text.toString() + " " + sum
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_layout, parent, false)

        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}