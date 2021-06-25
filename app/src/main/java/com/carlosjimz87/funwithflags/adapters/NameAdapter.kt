package com.carlosjimz87.funwithflags.adapters

import android.content.Context
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.utils.Languages

class NameAdapter {

    companion object {
        fun getTranslatedName(country: Country, context: Context): String {
            country.translations?.let {
                val translations = it.toMapString()
                val locale = Languages.getLocale(context)
                val contains = translations.containsKey(locale)
                return if (contains) translations[locale].toString() else country.name
            }
            return country.name
        }

        fun getTranslatedName(country: CountryDetails, context: Context): String {
            country.translations?.let {
                val translations = it.toMapString()
                val locale = Languages.getLocale(context)
                val contains = translations.containsKey(locale)
                return if (contains) translations[locale].toString() else country.name
            }
            return country.name
        }
    }
}