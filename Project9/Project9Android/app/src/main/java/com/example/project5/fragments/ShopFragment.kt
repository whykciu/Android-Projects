package com.example.project5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.project5.R
import com.example.project5.models.SharedViewModel
import com.example.project5.adapters.ShopListAdapter

class ShopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ShopListAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        recyclerView = view.findViewById(R.id.rvShoppingList)
        recyclerViewAdapter = ShopListAdapter(sharedViewModel, this.context, this)
        val layoutManager: LayoutManager = GridLayoutManager(this.context, 2)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = layoutManager

    }

}