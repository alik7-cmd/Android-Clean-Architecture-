package com.techascent.cleanarchitecture2.hilt

import com.techascent.cleanarchitecture2.api.FakeUserApiService
import com.techascent.cleanarchitecture2.data.common.NetworkModule
import com.techascent.cleanarchitecture2.data.user.api.UserApiInterface
import com.techascent.cleanarchitecture2.data.user.dto.UserServiceModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [UserServiceModule::class])
abstract class TestNetworkModule {

    @Binds
    @Singleton
    abstract fun provideFakeService(fakeUserApiService: FakeUserApiService) : UserApiInterface
}