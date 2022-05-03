package com.techascent.cleanarchitecture2.domain.common

import com.techascent.cleanarchitecture2.domain.repo.usecase.GitRepoUseCase
import com.techascent.cleanarchitecture2.domain.user.usecase.SaveUserUseCase
import com.techascent.cleanarchitecture2.domain.user.usecase.UserUseCase

data class UseCases(
    val saveUserUseCase: SaveUserUseCase,
    val userUseCase: UserUseCase,
    val gitRepoUseCase: GitRepoUseCase
)
