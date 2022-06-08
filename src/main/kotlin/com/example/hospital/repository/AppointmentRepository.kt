package com.example.hospital.repository

import com.example.hospital.model.Appointment
import org.springframework.data.jpa.repository.JpaRepository

interface AppointmentRepository:JpaRepository<Appointment, Long> {
    fun findAllByPatientId(patientId:Long):List<Appointment>
}