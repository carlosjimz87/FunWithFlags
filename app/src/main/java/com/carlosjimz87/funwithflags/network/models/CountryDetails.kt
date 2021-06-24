package com.carlosjimz87.funwithflags.network.models


data class CountryDetails(
    val alpha2Code: String?,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double?,
    val borders: List<String>,
    val callingCodes: List<String>,
    val capital: String,
    val currencies: List<Currency>,
    val demonym: String?,
    val flag: String,
    val gini: Double?,
    val languages: List<Language>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String?,
    val numericCode: String?,
    val population: Long?,
    val region: String?,
    val subregion: String?,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: Translations,
)