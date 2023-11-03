package com.example.plugins.services

import com.example.plugins.models.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object CompanyService {

    fun getCompanyById(companyId: Int): Company? {

        var company: Company? = null

        transaction {
            company = CompanyEntity.findById(companyId)?.toCompany()
        }

        return company

    }

    fun getAllCompanies(): List<Company>{

        var companies: MutableList<Company> = mutableListOf<Company>()

        transaction {
            val companyRow = CompanyEntity.all()
            for(r in companyRow){
                companies.add(r.toCompany())
            }
        }

        return companies

    }

    fun insertValue(company: Company){

        transaction {
            CompanyEntity.new{
                name = company.name
                country = company.country
                description = company.description
                numOfEmployees = company.numOfEmployees
            }
        }

    }

    fun updateValues(id: Int, newCompany: Company){

        transaction {
            Companies.update({Companies.id eq id}){
                it[name] = newCompany.name
                it[country] = newCompany.country
                it[description] = newCompany.description
                it[numOfEmployees] = newCompany.numOfEmployees
            }
        }

    }

    fun deleteRow(id: Int){

        transaction {
            Employees.deleteWhere { Employees.company eq id }
            Companies.deleteWhere { Companies.id eq id }
        }

    }

}