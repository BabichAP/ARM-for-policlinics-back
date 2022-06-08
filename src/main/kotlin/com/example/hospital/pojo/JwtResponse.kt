package com.example.hospital.pojo

data class JwtResponse(
    val token:String,
val type:String = "Bearer ",
val id:String,
val email:String,
val roles:List<String>)