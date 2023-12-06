package com.example.project5

import android.graphics.drawable.Drawable

data class Product(
    val name: String,
    val description: String,
    val img: Drawable,
    var quantity: Int,
    var stockLevel: Int
)
