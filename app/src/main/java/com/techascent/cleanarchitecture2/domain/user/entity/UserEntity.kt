package com.techascent.cleanarchitecture2.domain.user.entity

data class UserEntity(
    val id: Int?,
    val name: String?,
    val avatarUrl: String?,
    val bio: String?,
    val email: String?,
    val followers : Int?,
    val location : String?
)
