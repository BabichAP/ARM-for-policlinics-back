package com.example.hospital.repository

import com.example.hospital.model.Employee
import com.example.hospital.model.Role
import com.example.hospital.model.UserDetailImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
class UserService : UserDetailsService {

    @Autowired
    lateinit var employeeRepository:EmployeeRepository


    override fun loadUserByUsername(username: String?): UserDetails {
       return UserDetailImp
           .build(employeeRepository.findEmployeeByEmail(username!!).get())
    }

}