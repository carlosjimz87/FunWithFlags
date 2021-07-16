package com.carlosjimz87.funwithflags.repositories

import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import javax.inject.Inject

open class CountriesRepositoryImpl @Inject constructor(
    private val service: CountriesServiceImpl
) : CountriesRepository {
    override suspend fun getAllCountries(): ObserverResponse<List<Country>> {
        return service.getAllCountries()
    }

    override suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> {
        return service.getCountryDetails(countryId)
    }
}