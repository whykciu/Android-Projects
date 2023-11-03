package com.example.plugins.services

import com.example.plugins.models.Product
import com.example.plugins.models.ProductEntity
import com.example.plugins.models.Products
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ProductService {

    fun getProductById(productId: Int): Product? {

        var product: Product? = null

        transaction {
            product = ProductEntity.findById(productId)?.toProduct()
        }

        return product

    }

    fun getAllProducts(): List<Product>{

        var products: MutableList<Product> = mutableListOf<Product>()

        transaction {
            val productsRow = ProductEntity.all()
            for(r in productsRow){
                products.add(r.toProduct())
            }
        }

        return products

    }

}

