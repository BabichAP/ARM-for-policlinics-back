package com.example.hospital.controllers

import com.example.hospital.model.Employee
import com.example.hospital.model.Profession
import com.example.hospital.repository.EmployeeRepository
import com.example.hospital.service.HospitalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.PasswordAuthentication
import java.util.*
import kotlin.NoSuchElementException


@RestController
@RequestMapping("/employee")
class EmployeeController(private val service: HospitalService){


    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e:NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/all")
    fun getEmployees():List<Employee?> = employeeRepository.findAll()


    @GetMapping("/byId/{id}")
    fun getEmployee(@PathVariable id: UUID): Employee? = employeeRepository.findByIdOrNull(id)

    @GetMapping("/byName/{name}")
    fun getEmployee(@PathVariable name: String): MutableCollection<Employee?>? {
        val response: MutableList<Employee?> = mutableListOf()
        employeeRepository.findAll().forEach {
            if (it?.name == name) {
                response.add(it)
            }
        }
        return response
    }

    @PostMapping("/add")
    fun createNewArticle(@RequestBody employee: Employee): Employee =
        employeeRepository.save(employee)
    }