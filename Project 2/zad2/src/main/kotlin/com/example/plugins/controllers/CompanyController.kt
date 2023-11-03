package com.example.plugins.controllers

import com.example.plugins.models.Company
import com.example.plugins.services.CompanyService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.companyRouting(){

    get("/company/{id}") {

        val id = call.parameters["id"]?.toInt() ?: -1
        val result = CompanyService.getCompanyById(id)

        if(result != null){
            call.respond(result)
        }

    }

    get("/company/all") {

        val result = CompanyService.getAllCompanies()
        call.respond(result)

    }

    post("/company"){

        val request = call.receive<Company>()
        CompanyService.insertValue(request)
        call.respond(HttpStatusCode.OK, "Company added")

    }

    put("/company/{id}"){

        val id = call.parameters["id"]?.toInt() ?: -1
        val request = call.receive<Company>()
        CompanyService.updateValues(id, request)
        call.respond(HttpStatusCode.OK, "Company updated")

    }

    delete("/company/{id}"){

        val id = call.parameters["id"]?.toInt() ?: -1
        CompanyService.deleteRow(id)
        call.respond(HttpStatusCode.OK, "Company deleted")

    }


}