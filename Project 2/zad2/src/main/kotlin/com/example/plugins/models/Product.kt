package com.example.plugins.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Products : IntIdTable(){
    val name = varchar("name", 255)
    val country = varchar("country", 255)
    val description = varchar("description", 1024)
    val weight = integer("weight")
}

class ProductEntity(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<ProductEntity>(Products)
    var name by Products.name
    var country by Products.country
    var description by Products.description
    var weight by Products.weight

    fun toProduct() = Product(id.value, name, country, description, weight)

}

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val country: String,
    val description: String,
    val weight: Int
)
