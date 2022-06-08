package com.example.hospital.repository

import com.example.hospital.model.Doctor
import org.springframework.data.jpa.repository.JpaRepository

interface DoctorRepository:JpaRepository<Doctor, Long> {
    fun getDoctorByName(name:String):Doctor
}