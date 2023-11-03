package com.example.plugins.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Employees : IntIdTable(){
    val name = varchar("name", 255)
    val surname = varchar("surname", 255)
    val address = varchar("address", 255)
    val company = reference("company", Companies, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
}

class EmployeeEntity(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<EmployeeEntity>(Employees)
    var name by Employees.name
    var surname by Employees.surname
    var address by Employees.address
    var company by CompanyEntity referencedOn Employees.company

    fun toEmployee() = Employee(id.value, name, surname, address, company.toCompany())

}

@Serializable
data class Employee(
    val id: Int,
    val name: String,
    val surname: String,
    val address: String,
    val company: Company
)

@Serializable
data class EmployeeEntry(
    val name: String,
    val surname: String,
    val address: String,
    val companyId: Int
)