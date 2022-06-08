package com.example.hospital.repository

import com.example.hospital.model.ERole
import com.example.hospital.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RoleRepository : JpaRepository<Role?, Long?>{
    fun findByName(role:ERole):Optional<Role>
}