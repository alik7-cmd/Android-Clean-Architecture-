package com.techascent.cleanarchitecture2.data.login.remote

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.login.dto.LoginRequest
import com.techascent.cleanarchitecture2.data.login.dto.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("users/login")
    fun login(@Body request: LoginRequest): WrappedResponse<LoginResponse>

}