package com.example.plugins.controllers

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        productRouting()
        employeeRouting()
        companyRouting()
    }

}
