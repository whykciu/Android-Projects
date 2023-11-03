package com.example.plugins.services

import com.example.plugins.models.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object EmployeeService {

    fun getEmployeeById(employeeId: Int): Employee? {

        var employee: Employee? = null

        transaction {
            employee = EmployeeEntity.findById(employeeId)?.toEmployee()
        }

        return employee

    }

    fun getAllEmployees(): List<Employee>{

        var employees: MutableList<Employee> = mutableListOf<Employee>()

        transaction {
            val employeeRow = EmployeeEntity.all()
            for(r in employeeRow){
                employees.add(r.toEmployee())
            }
        }

        return employees

    }

    fun insertValue(employee: EmployeeEntry){

            transaction {
                val companyReference = CompanyEntity?.findById(employee.companyId)

                if(companyReference != null){
                    companyReference.numOfEmployees++
                    EmployeeEntity.new{
                        name = employee.name
                        surname = employee.surname
                        address = employee.address
                        company = CompanyEntity[companyReference.id]
                    }

                }
            }

    }

    fun updateValues(id: Int, newEmployee: EmployeeEntry){

            transaction {
                val companyReference = CompanyEntity?.findById(newEmployee.companyId)
                if(companyReference != null){

                    val oldId = EmployeeEntity.findById(id)?.toEmployee()?.company?.id
                    if(oldId != newEmployee.companyId){
                        CompanyEntity.findById(oldId!!)!!.numOfEmployees--
                        CompanyEntity.findById(newEmployee.companyId)!!.numOfEmployees++
                    }

                    Employees.update({Employees.id eq id}){
                        it[name] = newEmployee.name
                        it[surname] = newEmployee.surname
                        it[address] = newEmployee.address
                        it[company] = newEmployee.companyId
                    }

                }
            }

    }

    fun deleteRow(id: Int){

        transaction {
            CompanyEntity.findById(EmployeeEntity.findById(id)!!.company.id)!!.numOfEmployees--
            Employees.deleteWhere { Employees.id eq id }
        }

    }

}