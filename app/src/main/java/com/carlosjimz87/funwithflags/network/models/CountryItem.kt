package com.carlosjimz87.funwithflags.network.models

import com.squareup.moshi.Json

data class CountryItem(
    val name: String,
    @Json(name = "alpha3Code") val code: String,
)
