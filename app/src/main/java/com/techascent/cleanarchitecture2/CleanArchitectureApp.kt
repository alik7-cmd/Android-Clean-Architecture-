package com.techascent.cleanarchitecture2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CleanArchitectureApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}