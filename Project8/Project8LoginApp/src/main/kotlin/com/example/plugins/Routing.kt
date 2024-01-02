package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        post("/login") {
            val loginData = call.receive<Login>()
            try {
                if (LoginService.isCorrectData(loginData.login, loginData.password)){
                    call.respond("Success")
                }
            } catch (e : Exception) {
                call.respond("LoginError: " + e.message)
            }
        }

        post("/register"){
            val newUser = call.receive<User>()
            try{
                if(LoginService.addUser(newUser)){
                    call.respond("Success")
                } else {
                    call.respond("Fail")
                }
            } catch (e: Exception) {
                call.respond("RegisterError: " + e.message)
            }
        }

    }
}
