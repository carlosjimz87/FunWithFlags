package com.carlosjimz87.funwithflags.network.api

import com.carlosjimz87.funwithflags.network.BASE_URL
import com.carlosjimz87.funwithflags.network.Endpoints
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryItem
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val api_type = "rest"
private const val api_version = "v2"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountriesApiService {
    @GET("$api_type/$api_version/${Endpoints.GET_ALL_COUNTRIES}")
    suspend fun getAllCountries(): ObserverResponse<List<CountryItem>>

    @GET("$api_type/$api_version/${Endpoints.GET_COUNTRY_DETAILS}")
    suspend fun getCountryDetails(@Path(value = "country_code") countryId: String): ObserverResponse<CountryDetails>
}

object CountriesApi {
    val retrofitService: CountriesApiService by lazy {
        retrofit.create(CountriesApiService::class.java)
    }
}