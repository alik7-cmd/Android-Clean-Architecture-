package com.techascent.cleanarchitecture2.data.login.dto

data class LoginResponse(val userId : String, val email: String,
                         val userType : String,  val userName: String)
