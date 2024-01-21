package com.example.project5.models

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String,
    val img: Int,
    var quantity: Int,
    var price: BigDecimal,
    var stockLevel: Int
)
