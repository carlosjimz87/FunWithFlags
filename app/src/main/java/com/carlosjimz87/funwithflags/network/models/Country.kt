package com.carlosjimz87.funwithflags.network.models

import com.squareup.moshi.Json

data class Country(
    val name: String,
    @Json(name = "alpha3Code") val code: String,
    val flag: String,
    val translations: Translations?,
)
