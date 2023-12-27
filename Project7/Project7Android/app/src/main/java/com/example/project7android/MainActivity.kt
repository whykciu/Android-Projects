package com.example.project7android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseUrl = "http://192.168.1.27:8080/"
    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
        .create(ApiInterface::class.java)

    private lateinit var productRV: RecyclerView
    private lateinit var categoryRV: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productLinearLayoutManager = LinearLayoutManager(this)
        val categoryLinearLayoutManager = LinearLayoutManager(this)

        productRV = findViewById(R.id.rvProducts)
        categoryRV = findViewById(R.id.rvCategories)

        productRV.layoutManager = productLinearLayoutManager
        categoryRV.layoutManager = categoryLinearLayoutManager

        getAllProducts()
        getAllCategories()

    }

    private fun getAllProducts(){

        val retrofitData = retrofitBuilder.getProducts()

        retrofitData.enqueue(object : Callback<List<ProductItem>?> {
            override fun onResponse(call: Call<List<ProductItem>?>, response: Response<List<ProductItem>?>) {
                val products = response.body()!!
                productAdapter = ProductAdapter(products)
                productRV.adapter = productAdapter
            }

            override fun onFailure(call: Call<List<ProductItem>?>, t: Throwable) {
                Log.d("ERROR","OnFailure: " + t.message)
            }
        })

    }

    private fun getAllCategories(){

        val retrofitData = retrofitBuilder.getCategories()

        retrofitData.enqueue(object : Callback<List<CategoryItem>?> {
            override fun onResponse(call: Call<List<CategoryItem>?>, response: Response<List<CategoryItem>?>) {
                val categories = response.body()!!
                categoryAdapter = CategoryAdapter(categories)
                categoryRV.adapter = categoryAdapter
            }

            override fun onFailure(call: Call<List<CategoryItem>?>, t: Throwable) {
                Log.d("MainActivity","OnFailure: " + t.message)
            }
        })

    }

}