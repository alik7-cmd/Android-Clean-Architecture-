package com.techascent.cleanarchitecture2.domain.login.usecase

import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.login.dto.LoginResponse
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.login.LoginRepository
import com.techascent.cleanarchitecture2.domain.login.data.LoginEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun execute (userName : String, password : String) : Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
        return loginRepository.login(userName, password)
    }
}