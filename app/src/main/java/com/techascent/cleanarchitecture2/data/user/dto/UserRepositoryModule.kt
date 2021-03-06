package com.techascent.cleanarchitecture2.data.user.dto

import com.techascent.cleanarchitecture2.data.common.NetworkModule
import com.techascent.cleanarchitecture2.data.user.UserLocalRepositoryImpl
import com.techascent.cleanarchitecture2.data.user.UserRepositoryImpl
import com.techascent.cleanarchitecture2.data.user.api.UserApiInterface
import com.techascent.cleanarchitecture2.data.user.dao.RoomDBModule
import com.techascent.cleanarchitecture2.data.user.dao.UserDao
import com.techascent.cleanarchitecture2.domain.user.UserLocalRepository
import com.techascent.cleanarchitecture2.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class UserRepositoryModule {

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

@InstallIn(SingletonComponent::class)
@Module(includes = [RoomDBModule::class])
class LocalUserRepoModule{
    @Provides
    @Singleton
    fun provideLocalUserRepo(dao: UserDao) : UserLocalRepository {
        return UserLocalRepositoryImpl(dao)
    }
}