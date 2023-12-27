package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val categoryId: Int,
    val price: Double
)
