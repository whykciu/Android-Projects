package com.example.project7android

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProducts(): Call<List<ProductItem>>

    @GET("categories")
    fun getCategories(): Call<List<CategoryItem>>

}