package com.example.plugins.controllers

import com.example.plugins.models.Company
import com.example.plugins.models.Employee
import com.example.plugins.models.EmployeeEntry
import com.example.plugins.services.CompanyService
import com.example.plugins.services.EmployeeService
import com.example.plugins.services.ProductService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.employeeRouting(){

    get("/employee/{id}") {

        val id = call.parameters["id"]?.toInt() ?: -1
        val result = EmployeeService.getEmployeeById(id)

        if(result != null){
            call.respond(result)
        }

    }

    get("/employee/all") {

        val result = EmployeeService.getAllEmployees()
        call.respond(result)

    }

    post("/employee"){

        val request = call.receive<EmployeeEntry>()
        EmployeeService.insertValue(request)
        call.respond(HttpStatusCode.OK, "Employee added")
    }

    put("/employee/{id}"){

        val id = call.parameters["id"]?.toInt() ?: -1
        val request = call.receive<EmployeeEntry>()
        EmployeeService.updateValues(id, request)
        call.respond(HttpStatusCode.OK, "Employee updated")

    }

    delete("/employee/{id}"){

        val id = call.parameters["id"]?.toInt() ?: -1
        EmployeeService.deleteRow(id)
        call.respond(HttpStatusCode.OK, "Employee deleted")

    }

}
