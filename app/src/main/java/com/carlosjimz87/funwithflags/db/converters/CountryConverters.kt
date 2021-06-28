package com.carlosjimz87.funwithflags.db.converters

import com.carlosjimz87.funwithflags.db.models.CountryDisk
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails


fun CountryDisk.toCountry(): Country {
        return Country(
            name = this.name,
            code = this.alpha3Code,
            flag = this.flag,
            translations = this.translations
        )
    }

fun List<CountryDisk>.toCountryList(): List<Country> {
    return this.map {
        it.toCountry()
    }
}

fun CountryDetails.toCountryDisk(): CountryDisk {
    return CountryDisk(
        alpha3Code = this.alpha3Code,
        callingCodes = this.callingCodes,
        capital = this.capital,
        currencies =this.currencies,
        flag = this.flag,
        latlng = this.latlng,
        name = this.name,
        nativeName = this.nativeName!!,
        demonym = this.demonym!!,
        population = this.population!!,
        region = this.region!!,
        timezones = this.timezones,
        translations = this.translations!!
    )
}