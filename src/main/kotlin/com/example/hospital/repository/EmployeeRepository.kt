package com.example.hospital.repository

import com.example.hospital.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository : JpaRepository<Employee, UUID>{
    fun findEmployeeByEmail(email:String):Optional<Employee>
    fun existsByEmail(email: String):Boolean
}