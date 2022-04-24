package com.techascent.cleanarchitecture2.data.user.api

import com.techascent.cleanarchitecture2.data.user.dto.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiInterface {

    @GET("/users/{username}")
    suspend fun getUser(@Path ("username") username: String) : User
}