package com.techascent.cleanarchitecture2.domain.user.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class LocalUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val avatarUrl: String?,
    val bio: String?,
    val email: String?,
    val followers: Int?,
    val location: String?
) {
    constructor( name: String?,
                 avatarUrl: String?,
                 bio: String?,
                 email: String?,
                 followers: Int?,
                 location: String? ) : this(0, name,avatarUrl,bio,email,followers,location)
}
