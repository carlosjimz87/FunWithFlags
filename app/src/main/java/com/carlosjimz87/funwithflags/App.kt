package com.carlosjimz87.funwithflags

import android.app.Application
import com.carlosjimz87.funwithflags.db.CountriesDatabase
import timber.log.Timber

class App : Application() {
    val database: CountriesDatabase by lazy { CountriesDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
