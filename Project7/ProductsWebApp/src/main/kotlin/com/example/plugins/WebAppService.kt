package com.example.plugins

class WebAppService {

    private var categories: MutableList<Category>
    private var products: MutableList<Product>

    init {
        categories = mutableListOf(
            Category(1, "IT"),
            Category(2, "Fashion"),
            Category(3, "Sport"),
            Category(4, "Home")
        )

        products = mutableListOf(
            Product(1, "Laptop", 1, 3000.99),
            Product(2, "PC", 1, 5234.5),
            Product(3, "T-Shirt", 2, 100.0),
            Product(4, "Ball", 3, 50.0),
            Product(5, "Bike", 3, 1403.23),
            Product(6, "Sofa", 4, 1200.1),
            Product(7, "Carpet", 4, 324.99)
        )

    }

    fun getAllProducts() = products

    fun getAllCategories() = categories

}