package com.example.project5

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class ShopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ShopListAdapter
    private lateinit var productList: List<Product>

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

        val drawable: Drawable = R.drawable.burger.toDrawable()

        productList = listOf(
            Product("Burger", "opis1", drawable, 15),
            Product("Pizza", "opis2",  drawable, 15),
            Product("Lasagna", "opis3",  drawable, 15),
            Product("Salad", "opis4",  drawable, 15),
            Product("French Fries", "opis5",  drawable, 15),
            Product("Sandwich", "opis6",  drawable, 15),
            Product("Shake", "opis7",  drawable, 15),
            Product("Kebab", "opis8",  drawable, 15),
            Product("Spaghetti", "opis9",  drawable, 15)
        )

        recyclerView = view.findViewById(R.id.rvShoppingList)
        recyclerViewAdapter = ShopListAdapter(productList, this.context)
        val layoutManager: LayoutManager = GridLayoutManager(this.context, 2)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = layoutManager

    }

}