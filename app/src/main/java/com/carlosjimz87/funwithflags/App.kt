package com.carlosjimz87.funwithflags

import android.app.Application
import android.net.ConnectivityManager
import timber.log.Timber


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        if (instance == null) {
            instance = this
        }
    }

    private val isNetworkConnected: Boolean
        get() {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting
        }

    companion object {
        var instance: App? = null
            private set

        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected
        }
    }
}
