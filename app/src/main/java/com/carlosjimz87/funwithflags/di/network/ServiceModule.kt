package com.carlosjimz87.funwithflags.di.network

import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ServiceModule {
    @Provides
    fun providesNetworkService(apiService: CountriesApi): CountriesServiceImpl {
        return CountriesServiceImpl(apiService)
    }
}