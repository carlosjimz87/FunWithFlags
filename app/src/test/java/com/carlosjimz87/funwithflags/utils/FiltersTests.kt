package com.carlosjimz87.funwithflags.utils

import com.carlosjimz87.funwithflags.network.models.Country
import org.junit.Assert
import org.junit.Test

class FiltersTests {

    private val mockCountries = mutableListOf(
        Country("Argentina", "ARG", "https://restcountries.eu/data/arg.svg", null),
        Country("Brazil", "BRA", "https://restcountries.eu/data/bra.svg", null),
        Country("Canada", "CAN", "https://restcountries.eu/data/can.svg", null),
        Country("Colombia", "COL", "https://restcountries.eu/data/col.svg", null),
        Country("France", "FRA", "https://restcountries.eu/data/fra.svg", null),
        Country("Spain", "ESP", "https://restcountries.eu/data/esp.svg", null),
        Country("Great Britain", "GBR", "https://restcountries.eu/data/gbr.svg", null),
        Country("United States", "USA", "https://restcountries.eu/data/usa.svg", null),
    )

    @Test
    fun filterListCountryTest() {
        Assert.assertEquals(1, filterListCountry("Es", mockCountries).size)
        Assert.assertEquals(1, filterListCountry("es", mockCountries).size)
        Assert.assertEquals(1, filterListCountry("fr", mockCountries).size)
        Assert.assertEquals(1, filterListCountry("GB", mockCountries).size)

    }

    @Test
    fun criteriaTest() {
        Assert.assertEquals(false, criteria("Es", "ESP"))
        Assert.assertEquals(true, criteria("COL", "co"))
    }
}