package com.techascent.cleanarchitecture2.domain.user

import com.techascent.cleanarchitecture2.domain.user.entity.LocalUserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {

    suspend fun saveUser(user : LocalUserEntity)

    suspend fun deleteUser(user :LocalUserEntity )

    suspend fun observeAllUser() : Flow<List<LocalUserEntity>>
}