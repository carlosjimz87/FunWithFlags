package com.carlosjimz87.funwithflags.adapters

import android.content.Context
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.utils.getLocale

fun getTranslatedName(country: Country, context: Context): String {
    country.translations?.let {
        val translations =country.translations.toMapString(country.name)
        return translate(translations, context)
    }
    return country.name
}

fun getTranslatedName(country: CountryDetails, context: Context): String {
    country.translations?.let {
        val translations =country.translations.toMapString(country.name)
        return translate(translations, context)
    }
    return country.name
}

private fun translate(
    translations: Map<String, String?>,
    context: Context,
): String {
    val trans = translations[getLocale(context)]
    trans?.let {
        return trans
    }
    return translations["en"].toString()
}
