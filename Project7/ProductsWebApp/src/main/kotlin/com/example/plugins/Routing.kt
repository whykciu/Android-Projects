package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    var appService = WebAppService()

    routing {

        get("/products"){
            call.respond(appService.getAllProducts())
        }

        get("/categories"){
            call.respond(appService.getAllCategories())
        }

    }

}
