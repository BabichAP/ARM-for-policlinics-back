package com.example.hospital.controllers

import com.example.hospital.model.Appointment
import com.example.hospital.model.Patient
import com.example.hospital.repository.AppointmentRepository
import com.example.hospital.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/appointment")
class AppointmentControler {
    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository
    @GetMapping("/allById/")
    fun getAppointments(@RequestParam(name = "id")  id: Long): List<Appointment> = appointmentRepository.findAllByPatientId(id)
    @PostMapping("/add")
    fun saveAppointment(@RequestBody appointment: Appointment): Appointment {
        val a = appointment
       return appointmentRepository.save(a)
    }
    @GetMapping("/delete")
    fun deleteAppointment(@RequestParam(name = "id") id:Long) = appointmentRepository.delete(appointmentRepository.findByIdOrNull(id)!!)
}