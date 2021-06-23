package com.carlosjimz87.funwithflags.repositories

import com.carlosjimz87.funwithflags.network.api.CountriesApiService
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryItem
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse

class CountriesRepositoryImpl(private val service: CountriesApiService) : CountriesRepository {
    override suspend fun getAllCountries(): ObserverResponse<List<CountryItem>> {
        return service.getAllCountries()
    }

    override suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> {
        return service.getCountryDetails(countryId)
    }
}