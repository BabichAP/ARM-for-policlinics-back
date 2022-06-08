package com.example.hospital.controllers

import com.example.hospital.model.Doctor
import com.example.hospital.repository.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/doctor")
class DoctorController {

    @Autowired
    private lateinit var doctorRepository: DoctorRepository

    @GetMapping("/all")
    fun getDoctors():List<Doctor>? = doctorRepository.findAll()

}