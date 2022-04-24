package com.techascent.cleanarchitecture2.data.user.dto

import com.techascent.cleanarchitecture2.data.common.NetworkModule
import com.techascent.cleanarchitecture2.data.user.UserRepositoryImpl
import com.techascent.cleanarchitecture2.data.user.api.UserApiInterface
import com.techascent.cleanarchitecture2.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    @Singleton
    fun provideUserRepository(userApiInterface: UserApiInterface) : UserRepository{
        return UserRepositoryImpl(userApiInterface)
    }


}


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class UserServiceModule{

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit) : UserApiInterface {
        return retrofit.create(UserApiInterface::class.java)
    }

}