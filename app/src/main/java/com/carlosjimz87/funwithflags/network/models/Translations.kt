package com.carlosjimz87.funwithflags.network.models


data class Translations(
    val es: String?,
    val de: String?,
    val fr: String?,
    val pt: String?,
    val ja: String?,
    val fa: String?,
    val it: String?,
) {
    fun toMapString(): Map<String, String?> {

        return mapOf(
            "es" to es,
            "de" to de,
            "fr" to fr,
            "pt" to pt,
            "ja" to ja,
            "fa" to fa,
            "it" to it,
        )
    }
}