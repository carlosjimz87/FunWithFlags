package com.carlosjimz87.funwithflags


import com.carlosjimz87.funwithflags.network.api.ApiManager
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl

object ServiceLocator {

    @Volatile
    var countriesRepository: CountriesRepository? = null

    private fun createCountriesRepository(): CountriesRepository {
        val newRepo = CountriesRepositoryImpl(CountriesServiceImpl(ApiManager.retrofitService))
        countriesRepository = newRepo
        return newRepo
    }

    fun provideCountriesRepository(): CountriesRepository {
        synchronized(this) {
            return countriesRepository ?: createCountriesRepository()
        }
    }
}