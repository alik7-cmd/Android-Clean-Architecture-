package com.techascent.cleanarchitecture2.data.user

import com.techascent.cleanarchitecture2.data.user.dao.UserDao
import com.techascent.cleanarchitecture2.domain.user.UserLocalRepository
import com.techascent.cleanarchitecture2.domain.user.entity.LocalUserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalRepositoryImpl @Inject constructor(private val dao: UserDao) : UserLocalRepository {
    override suspend fun saveUser(user: LocalUserEntity) {
       dao.insert(user)
    }

    override suspend fun deleteUser(user: LocalUserEntity) {
        dao.delete(user)
    }

    override suspend fun observeAllUser(): Flow<List<LocalUserEntity>> {
        return dao.observeAllUser()
    }
}