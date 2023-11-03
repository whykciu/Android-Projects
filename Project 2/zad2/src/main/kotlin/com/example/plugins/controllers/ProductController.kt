package com.example.plugins.controllers

import com.example.plugins.services.ProductService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productRouting(){

    get("/product/{id}") {

        val id = call.parameters["id"]?.toInt() ?: -1
        val result = ProductService.getProductById(id)

        if(result != null){
            call.respond(result)
        }

    }

    get("/product/all") {

        val result = ProductService.getAllProducts()
        call.respond(result)

    }

}
