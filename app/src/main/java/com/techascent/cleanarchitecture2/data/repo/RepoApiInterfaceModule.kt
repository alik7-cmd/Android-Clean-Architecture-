package com.techascent.cleanarchitecture2.data.repo

import com.techascent.cleanarchitecture2.data.common.NetworkModule
import com.techascent.cleanarchitecture2.data.repo.api.RepoApiInterface
import com.techascent.cleanarchitecture2.domain.repo.GitRepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepoApiInterfaceModule {

    @Provides
    @Singleton
    fun provideRepoApiInterface(retrofit: Retrofit) : RepoApiInterface{
        return retrofit.create(RepoApiInterface::class.java)
    }
}

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class GitRepoRepositoryModule{

    @Provides
    @Singleton
    fun provideRepoRepository(apiInterface: RepoApiInterface) : GitRepoRepository{
        return GitRepoRepositoryImpl(apiInterface)
    }

}