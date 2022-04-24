package com.techascent.cleanarchitecture2.data.login

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.login.dto.LoginRequest
import com.techascent.cleanarchitecture2.data.login.dto.LoginResponse
import com.techascent.cleanarchitecture2.data.login.remote.LoginApi
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.login.LoginRepository
import com.techascent.cleanarchitecture2.domain.login.data.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(val loginApi: LoginApi) : LoginRepository {
    override suspend fun login(userName : String, password : String): Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
        return flow {
            val loginResponse = loginApi.login(LoginRequest(userName, password))
            val loginEntity = LoginEntity(loginResponse.data!!.userId, loginResponse.data!!.email,
                loginResponse.data!!.userType, loginResponse.data!!.userName)
            emit(BaseResult.Success(loginEntity))
        }
    }
}