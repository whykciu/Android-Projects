package com.example.project5.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project5.models.PaymentModel
import com.example.project5.R

class PaymentAdapter(private var paymentModels: List<PaymentModel>) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>(){

    private var onItemClickListener: OnItemClickListener? = null

    class PaymentViewHolder(view: View): RecyclerView.ViewHolder(view){

        val nameTv: TextView = view.findViewById(R.id.tvPaymentName)
        val totalTv: TextView = view.findViewById(R.id.tvTotalPayment)

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false)
        return PaymentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return paymentModels.size
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {

        val paymentModel = paymentModels[position]

        holder.nameTv.text = holder.nameTv.text.toString() + position.toString()
        holder.totalTv.text = holder.totalTv.text.toString() + paymentModel.paymentData.total.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

    }

    fun updateData(newData: List<PaymentModel>){
        paymentModels = newData
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int?)
    }

}