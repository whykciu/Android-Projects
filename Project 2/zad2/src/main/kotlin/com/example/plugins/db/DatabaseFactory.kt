package com.example.plugins.db

import com.example.plugins.models.Companies
import com.example.plugins.models.Employees
import com.example.plugins.models.ProductEntity
import com.example.plugins.models.Products
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(){

        val database = Database.connect("jdbc:sqlite:identifier.sqlite", "org.sqlite.JDBC")

        transaction(database) {
            SchemaUtils.create(Products)
            SchemaUtils.create(Companies)
            SchemaUtils.create(Employees)
            /*ProductEntity.new {
                name = "Laptop"
                country = "USA"
                description = "Opis1"
                weight = 2
            }
            ProductEntity.new {
                name = "Komputer"
                country = "Polska"
                description = "Opis2"
                weight = 5
            }*/
        }

    }

}