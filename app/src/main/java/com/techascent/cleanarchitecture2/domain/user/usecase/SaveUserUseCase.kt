package com.techascent.cleanarchitecture2.domain.user.usecase

import com.techascent.cleanarchitecture2.domain.user.UserLocalRepository
import com.techascent.cleanarchitecture2.domain.user.entity.LocalUserEntity
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val localRepository: UserLocalRepository) {

    suspend fun execute(user :LocalUserEntity)  {
        return localRepository.saveUser(user)
    }
}