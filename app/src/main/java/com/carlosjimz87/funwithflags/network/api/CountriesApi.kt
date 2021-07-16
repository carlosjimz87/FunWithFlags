package com.carlosjimz87.funwithflags.network.api

import com.carlosjimz87.funwithflags.network.Endpoints
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import retrofit2.http.GET
import retrofit2.http.Path

private const val api_type = "rest"
private const val api_version = "v2"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .client(LoggerInterceptorClient)
//    .build()

interface CountriesApi {
    @GET("$api_type/$api_version/${Endpoints.GET_ALL_COUNTRIES}")
    suspend fun getAllCountries(): List<Country>

    @GET("$api_type/$api_version/${Endpoints.GET_COUNTRY_DETAILS}")
    suspend fun getCountryDetails(@Path(value = "country_code") countryId: String): CountryDetails
}

//object ApiManager {
//    val retrofitService: CountriesApi by lazy {
//        retrofit.create(CountriesApi::class.java)
//    }
//}