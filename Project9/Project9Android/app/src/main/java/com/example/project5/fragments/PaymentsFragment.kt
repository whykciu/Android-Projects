package com.example.project5.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project5.BoughtItemsActivity
import com.example.project5.adapters.PaymentAdapter
import com.example.project5.R
import com.example.project5.models.SharedViewModel

class PaymentsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: PaymentAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_payments_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        recyclerView = view.findViewById(R.id.rvPayments)

        recyclerViewAdapter = PaymentAdapter(sharedViewModel.getPayments().value!!)
        sharedViewModel.getPayments().observe(viewLifecycleOwner) { payments ->
            recyclerViewAdapter.updateData(payments)
        }

        recyclerViewAdapter.setOnItemClickListener(object : PaymentAdapter.OnItemClickListener {
            override fun onItemClick(position: Int?) {
                val boughtItemsList: MutableList<Pair<String,String>> = mutableListOf()

                for (item in sharedViewModel.getPayments()!!.value!![position!!].boughtItems){
                    boughtItemsList.add(Pair(item.product.name, item.quantity.toString()))
                }

                val names = boughtItemsList.map { it.first }.toTypedArray()
                val quantities = boughtItemsList.map { it.second }.toTypedArray()

                val intent = Intent(context, BoughtItemsActivity::class.java)
                intent.putExtra("BOUGHT_ITEMS_NAMES", names)
                intent.putExtra("BOUGHT_ITEMS_QUANTITIES", quantities)
                startActivity(intent)
            }
        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = layoutManager
    }


}