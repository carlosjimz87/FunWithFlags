package com.carlosjimz87.funwithflags.repositories

import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryItem
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse

interface CountriesRepository {
    suspend fun getAllCountries(): ObserverResponse<List<CountryItem>>
    suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails>
}