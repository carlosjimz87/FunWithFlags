package com.carlosjimz87.funwithflags.network.models


data class CountryDetails(
    val alpha2Code: String?,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double?,
    val callingCodes: List<String>,
    val capital: String,
    val currencies: List<Currency>,
    val demonym: String?,
    val flag: String,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String?,
    val population: Long?,
    val region: String?,
    val timezones: List<String>,
    val translations: Translations? = null,
)