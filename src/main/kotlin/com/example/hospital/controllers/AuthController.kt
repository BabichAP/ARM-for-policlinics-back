package com.example.hospital.controllers

import com.example.hospital.config.jwt.JwtUtils
import com.example.hospital.model.ERole
import com.example.hospital.model.Employee
import com.example.hospital.model.Role
import com.example.hospital.model.UserDetailImp
import com.example.hospital.pojo.JwtResponse
import com.example.hospital.pojo.LoginRequest
import com.example.hospital.pojo.MessageResponse
import com.example.hospital.pojo.SignUpRequest
import com.example.hospital.repository.EmployeeRepository
import com.example.hospital.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.function.Consumer
import java.util.stream.Collectors


@RestController
@RequestMapping("")
class AuthController {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @PostMapping("/login")
    fun authUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*>? {
        val authentication: Authentication = authenticationManager!!
            .authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.email,
                    loginRequest.password
                )
            )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails: UserDetailImp = authentication.principal as UserDetailImp
        val roles: List<String> = userDetails.authorities.stream()
            .map { item -> item.authority }
            .collect(Collectors.toList())
        return ResponseEntity.ok(
            JwtResponse(
                jwt,
                id = userDetails.id.toString(),
                email = userDetails.email,
                roles = roles
            )
        )
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody signupRequest: SignUpRequest): ResponseEntity<*>? {

        if (employeeRepository.existsByEmail(signupRequest.email)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Email is exist"))
        }
        val user = Employee(
            email = signupRequest.email,
            name = signupRequest.name,
            surname = signupRequest.surname,
            patronymic = signupRequest.patronymic,
            password = passwordEncoder!!.encode(signupRequest.password),
            phoneNumber = signupRequest.phoneNumber,
            roles = setOf()
        )
        val reqRoles: Set<String> = signupRequest.authorities
        val roles: MutableSet<Role> = HashSet()
        reqRoles.forEach(Consumer { r: String? ->
            when (r) {
                "admin" -> {
                    val adminRole: Role = roleRepository!!
                        .findByName(ERole.ROLE_ADMIN)
                        .orElseThrow {
                            RuntimeException(
                                "Error, Role ADMIN is not found"
                            )
                        }
                    roles.add(adminRole)
                }
                "mod" -> {
                    val modRole: Role = roleRepository!!
                        .findByName(ERole.ROLE_USER)
                        .orElseThrow {
                            RuntimeException(
                                "Error, Role MODERATOR is not found"
                            )
                        }
                    roles.add(modRole)
                }
                else -> {
                    val userRole: Role = roleRepository!!.findByName(ERole.ROLE_USER)
                        .orElseThrow {
                            RuntimeException(
                                "Error, Role USER is not found"
                            )
                        }
                    roles.add(userRole)
                }
            }
        })
        user.roles = roles
        employeeRepository.save(user)
        return ResponseEntity.ok(MessageResponse("User CREATED"))
    }
}