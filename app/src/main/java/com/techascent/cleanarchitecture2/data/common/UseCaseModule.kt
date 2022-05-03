package com.techascent.cleanarchitecture2.data.common

import com.techascent.cleanarchitecture2.domain.common.UseCases
import com.techascent.cleanarchitecture2.domain.repo.usecase.GitRepoUseCase
import com.techascent.cleanarchitecture2.domain.user.usecase.SaveUserUseCase
import com.techascent.cleanarchitecture2.domain.user.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideAllUseCase(saveUserUseCase: SaveUserUseCase,
                          userUseCase: UserUseCase,
                          gitRepoUseCase: GitRepoUseCase) : UseCases{
        return UseCases(saveUserUseCase, userUseCase, gitRepoUseCase)
    }
}