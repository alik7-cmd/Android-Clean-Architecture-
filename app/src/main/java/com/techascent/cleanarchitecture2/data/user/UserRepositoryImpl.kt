package com.techascent.cleanarchitecture2.data.user

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.user.api.UserApiInterface
import com.techascent.cleanarchitecture2.data.user.dto.User
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.user.UserRepository
import com.techascent.cleanarchitecture2.domain.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApiInterface: UserApiInterface) : UserRepository {
    override suspend fun getUser(username: String): Flow<BaseResult<UserEntity, WrappedResponse<User>>> {
        return flow {
            val user = userApiInterface.getUser(username)
            val entity = UserEntity(user.id, user.name, user.avatar_url, user.bio, user.email, user.followers, user.location)
            emit(BaseResult.Success(entity))
        }
    }
}