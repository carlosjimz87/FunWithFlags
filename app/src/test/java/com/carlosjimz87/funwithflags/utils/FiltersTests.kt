package com.carlosjimz87.funwithflags.utils

import com.carlosjimz87.funwithflags.network.models.Country
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.lessThan
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
        assertThat(filterListCountry("Es", mockCountries).size, lessThan(mockCountries.size))
        assertThat(filterListCountry("es", mockCountries).size, lessThan(mockCountries.size))
        assertThat(filterListCountry("fr", mockCountries).size, lessThan(mockCountries.size))
        assertThat(filterListCountry("GB", mockCountries).size, lessThan(mockCountries.size))
    }

    @Test
    fun codeCriteriaTest() {
        Assert.assertEquals("Argentina(${mockCountries[0].code}) contains `ARG`",
            true,
            codeCriteria(mockCountries[0], "ARG"))
        Assert.assertEquals("Brazil(${mockCountries[1].code}) contains `br`",
            true,
            codeCriteria(mockCountries[1], "br"))
        Assert.assertEquals("Canada(${mockCountries[2].code}) contains `ca`",
            true,
            codeCriteria(mockCountries[2], "ca"))
        Assert.assertEquals("Colombia(${mockCountries[3].code}) contains `OL`",
            false,
            codeCriteria(mockCountries[3], "OL"))
        Assert.assertEquals("France(${mockCountries[4].code}) contains `Fr`",
            true,
            codeCriteria(mockCountries[4], "Fr"))
        Assert.assertEquals("Spain(${mockCountries[5].code}) contains `Sp`",
            false,
            codeCriteria(mockCountries[5], "Sp"))
        Assert.assertEquals("Great Britain(${mockCountries[6].code}) contains `GB`",
            true,
            codeCriteria(mockCountries[6], "GB"))
        Assert.assertEquals("United States(${mockCountries[7].code}) contains `us`",
            true,
            codeCriteria(mockCountries[7], "us"))
    }


    @Test
    fun nameCriteriaTest() {
        Assert.assertEquals("Argentina(${mockCountries[0].code}) contains `ARG`",
            true,
            nameAndCodeCriteria(mockCountries[0], "ARG"))
        Assert.assertEquals("Brazil(${mockCountries[1].code}) contains `zil`",
            true,
            nameAndCodeCriteria(mockCountries[1], "zil"))
        Assert.assertEquals("Canada(${mockCountries[2].code}) contains `ca`",
            true,
            nameAndCodeCriteria(mockCountries[2], "ca"))
        Assert.assertEquals("Colombia(${mockCountries[3].code}) contains `OL`",
            true,
            nameAndCodeCriteria(mockCountries[3], "OL"))
        Assert.assertEquals("France(${mockCountries[4].code}) contains `ance`",
            true,
            nameAndCodeCriteria(mockCountries[4], "ance"))
        Assert.assertEquals("Spain(${mockCountries[5].code}) contains `Spa`",
            true,
            nameAndCodeCriteria(mockCountries[5], "Sp"))
        Assert.assertEquals("Great Britain(${mockCountries[6].code}) contains `Brit`",
            true,
            nameAndCodeCriteria(mockCountries[6], "Brit"))
        Assert.assertEquals("United States(${mockCountries[7].code}) contains `state`",
            true,
            nameAndCodeCriteria(mockCountries[7], "state"))
    }

}