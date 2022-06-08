package com.example.hospital.service


import com.example.hospital.model.Employee
import com.example.hospital.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class HospitalService() {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    fun getEmployees():List<Employee> = employeeRepository.findAll()
    fun login(email:String, password:String):Boolean {
        val employee= employeeRepository.findEmployeeByEmail(email).get()
        if(employee.password == password){
            return true
        }
        return false
    }

    fun register(employee:Employee):Boolean{
        employeeRepository.save(employee)
        return true
    }

}
