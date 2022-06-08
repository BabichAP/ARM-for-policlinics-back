package com.example.hospital.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*
import kotlin.jvm.Transient


@Entity
data class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Enumerated(EnumType.STRING)
    var name: ERole,


)
