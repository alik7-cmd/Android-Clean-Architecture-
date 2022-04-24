package com.techascent.cleanarchitecture2.domain.login

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.login.dto.LoginResponse
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.login.data.LoginEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(userName : String, password : String) : Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>>
}