package com.carlosjimz87.funwithflags.repositories

import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getAllCountries(): Flow<ObserverResponse<List<Country>>>
    suspend fun getCountryDetails(countryId: String): Flow<ObserverResponse<CountryDetails>>
}