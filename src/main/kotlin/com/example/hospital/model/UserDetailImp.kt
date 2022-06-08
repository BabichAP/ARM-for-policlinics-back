package com.example.hospital.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import kotlin.collections.Collection

class UserDetailImp( var id: Long,
                     var name: String,
                     var surname: String,
                     var patronymic: String,
                     var phoneNumber: String,
                     var email: String,
                     private var password: String,
                     private var authorities:MutableCollection<GrantedAuthority>): UserDetails {

    companion object{
        fun build(employee:Employee):UserDetailImp{
            return UserDetailImp(employee.id,
            employee.name,
            employee.surname,
            employee.patronymic,
            employee.phoneNumber,
            employee.email,
            employee.password,
            employee.roles.stream().map { role -> SimpleGrantedAuthority(role.name.toString()) }.collect(Collectors.toList()))
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}