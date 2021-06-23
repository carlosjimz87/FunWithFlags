package com.carlosjimz87.funwithflags.network

const val BASE_URL =
    "https://restcountries.eu/"

object Endpoints {
    const val GET_ALL_COUNTRIES = "all/"
    const val GET_COUNTRY_DETAILS = "alpha/{country_code}"
}