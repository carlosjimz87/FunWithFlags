package com.carlosjimz87.funwithflags.adapters

import android.content.Context
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.utils.getLocale

fun getTranslatedName(country: Country, context: Context): String {
    country.translations?.let {
        return translate(country.translations.toMapString(), context, country.name)
    }
//    Timber.i("TRANSLATED: $country.name (locale:${getLocale(context)}")
    return country.name
}

fun getTranslatedName(country: CountryDetails, context: Context): String {
    country.translations?.let {
        return translate(country.translations.toMapString(), context, country.name)
    }
//    Timber.i("TRANSLATED: country.name (locale:${getLocale(context)}")
    return country.name
}

private fun translate(
    translations: Map<String, String?>,
    context: Context,
    name: String,
): String {
    val trans = translations[getLocale(context).trim()].toString()
    return if (trans.isEmpty() || trans == "null") name else trans
}
