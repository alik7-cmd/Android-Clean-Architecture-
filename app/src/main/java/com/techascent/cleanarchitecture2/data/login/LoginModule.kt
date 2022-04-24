package com.techascent.cleanarchitecture2.data.login

import com.techascent.cleanarchitecture2.data.common.NetworkModule
import com.techascent.cleanarchitecture2.data.login.remote.LoginApi
import com.techascent.cleanarchitecture2.domain.login.LoginRepository
import com.techascent.cleanarchitecture2.domain.login.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginRepository(api: LoginApi) : LoginRepository{
        return LoginRepositoryImpl(api)
    }
}

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class AppServiceModule{
    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit) : LoginApi{
        return retrofit.create(LoginApi::class.java)
    }

}
