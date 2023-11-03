package com.example.plugins.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object Companies : IntIdTable(){
    var name = varchar("name", 255)
    val country = varchar("country", 255)
    val description = varchar("description",1024)
    val numOfEmployees = integer("numOfEmployees").default(0);
}

class CompanyEntity(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<CompanyEntity>(Companies)
    var name by Companies.name
    var country by Companies.country
    var description by Companies.description
    var numOfEmployees by Companies.numOfEmployees

    fun toCompany() = Company(id.value, name, country, description, numOfEmployees)
}

@Serializable
data class Company(
    val id: Int,
    var name: String,
    var country: String,
    var description: String,
    var numOfEmployees: Int
)

