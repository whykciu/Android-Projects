package com.example.project5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project5.adapters.BoughtItemsAdapter
import com.example.project5.models.SharedViewModel


class BoughtItemsActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: BoughtItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bought_products)

        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        val names = intent.getStringArrayExtra("BOUGHT_ITEMS_NAMES")
        val quantities = intent.getStringArrayExtra("BOUGHT_ITEMS_QUANTITIES")

        recyclerView = findViewById(R.id.rvBoughtItems)
        val payments = sharedViewModel.getPayments().value!!

        recyclerViewAdapter = BoughtItemsAdapter(names!!, quantities!!)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = layoutManager

    }

}