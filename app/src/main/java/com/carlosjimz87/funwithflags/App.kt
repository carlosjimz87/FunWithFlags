package com.carlosjimz87.funwithflags

import android.app.Application
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
