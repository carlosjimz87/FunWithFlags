package com.carlosjimz87.funwithflags.network.services

import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import kotlinx.coroutines.Dispatchers

open class CountriesServiceImpl(private val api: CountriesApi) : BaseService {
    suspend fun getAllCountries(): ObserverResponse<List<Country>> {
        return apiCall(Dispatchers.IO) {
            api.getAllCountries()
        }
    }

    suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> {
        return apiCall(Dispatchers.IO) {
            api.getCountryDetails(countryId)
        }
    }
}