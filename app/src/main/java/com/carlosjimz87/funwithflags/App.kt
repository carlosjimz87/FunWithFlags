package com.carlosjimz87.funwithflags

import android.app.Application
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import timber.log.Timber

class App : Application() {

    val countriesRepository: CountriesRepository
        get() = ServiceLocator.provideCountriesRepository()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
