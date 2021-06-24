package com.carlosjimz87.funwithflags.network.models

import com.squareup.moshi.Json


data class Language(
    @Json(name = "iso639_1")
    val iso6391: String?,
    @Json(name = "iso639_2")
    val iso6392: String?,
    val name: String,
    val nativeName: String?,
)