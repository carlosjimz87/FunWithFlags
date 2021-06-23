package com.carlosjimz87.funwithflags.network.models

import com.squareup.moshi.Json


data class Translations(
    @Json(name = "de") val deu: String,
    @Json(name = "es") val esp: String,
    @Json(name = "fr") val fra: String,
    @Json(name = "pr") val por: String,
    @Json(name = "it") val ita: String,
)