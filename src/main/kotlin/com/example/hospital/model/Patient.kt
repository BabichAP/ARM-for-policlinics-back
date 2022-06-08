package com.example.hospital.model

import ru.mirea.armforpolyclinics.domain.entity.Genders
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val surname: String,
    val patronymic: String,
    val gender: Genders,
    val address: String,
    val phoneNumber: String,
    val snils:String,
    val policyNumber: String,
    val bloodType:BloodTypes,
    val age: Int
)