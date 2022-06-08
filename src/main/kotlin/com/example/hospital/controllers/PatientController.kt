package com.example.hospital.controllers

import com.example.hospital.model.Patient
import com.example.hospital.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patient")
class PatientController(){


    @Autowired
    private lateinit var patientRepository: PatientRepository

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e:NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/all")
    fun getPatients():List<Patient>? = patientRepository.findAll()


    @PostMapping("/add")
    fun createPatient(@RequestBody patient: Patient): Patient = patientRepository.save(patient)

    @GetMapping("/delete/")
    fun deletePatient(@RequestParam(name = "id") id:Long) = patientRepository.delete(patientRepository.findByIdOrNull(id)!!)
}