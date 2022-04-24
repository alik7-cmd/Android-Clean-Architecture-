package com.techascent.cleanarchitecture2.data.common

import android.content.Context
import com.techascent.cleanarchitecture2.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPrefs{
        return SharedPrefs(context)
    }
}