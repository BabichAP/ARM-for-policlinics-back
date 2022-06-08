package com.example.hospital.pojo

data class SignUpRequest(var name: String,
                         var surname: String,
                         var patronymic: String,
                         var phoneNumber: String,
                         var email: String,
                          var password: String,
                          var authorities:Set<String>)
