package com.example.hospital.model

import java.util.*
import javax.persistence.*

@Entity
data class Appointment(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long,
    val dateOfAppointment: String,
    val doctorName:String,
    val profession: Profession,
    val cabinet: String,
    val patientId:Long
)
