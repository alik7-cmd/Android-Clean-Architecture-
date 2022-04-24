package com.techascent.cleanarchitecture2.domain.user.usecase

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.user.dto.User
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.user.UserRepository
import com.techascent.cleanarchitecture2.domain.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username : String) : Flow<BaseResult<UserEntity, WrappedResponse<User>>>{
        return userRepository.getUser(username)
    }
}