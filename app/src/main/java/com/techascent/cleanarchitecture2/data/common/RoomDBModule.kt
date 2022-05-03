package com.techascent.cleanarchitecture2.data.user.dao

import android.content.Context
import com.techascent.cleanarchitecture2.data.common.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)


}

@Module(includes = [RoomDBModule::class])
@InstallIn(SingletonComponent::class)
object UserDaoModule{

    @Provides
    fun provideUserDao (database: AppDatabase) : UserDao{
        return database.userDao()
    }

}