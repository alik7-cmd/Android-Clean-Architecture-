package com.techascent.cleanarchitecture2.domain.user

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.user.dto.User
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(username : String) : Flow<BaseResult<UserEntity, WrappedResponse<User>>>
}