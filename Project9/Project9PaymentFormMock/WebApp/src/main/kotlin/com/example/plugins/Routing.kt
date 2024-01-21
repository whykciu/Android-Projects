package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        post("/pay") {
            val paymentData = call.receive<PaymentData>()
            val response = PaymentService.checkPayment(paymentData)
            call.respond(response)
        }
    }
}
