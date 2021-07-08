package com.carlosjimz87.funwithflags.network

import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.Currency
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.repositories.CountriesRepository

class FakeCountriesRepository(
) : CountriesRepository {

    companion object {
        val countriesList: List<Country> = listOf(
            Country("Argentina", "ARG", "https://restcountries.eu/data/arg.svg", null),
            Country("Brazil", "BRA", "https://restcountries.eu/data/bra.svg", null),
            Country("Canada", "CAN", "https://restcountries.eu/data/can.svg", null),
            Country("Colombia", "COL", "https://restcountries.eu/data/col.svg", null),
            Country("France", "FRA", "https://restcountries.eu/data/fra.svg", null),
            Country("Spain", "ESP", "https://restcountries.eu/data/esp.svg", null),
            Country("Great Britain", "GBR", "https://restcountries.eu/data/gbr.svg", null),
            Country("United States", "USA", "https://restcountries.eu/data/usa.svg", null),
        )

        val countryDetails: CountryDetails = CountryDetails(
            alpha2Code = "ES",
            alpha3Code = "ESP",
            altSpellings = listOf("ES", "Reino de España"),
            callingCodes = listOf("34"),
            capital = "Madrid",
            currencies = listOf(
                Currency("EUR", "Euro", "€")
            ),
            demonym = "Español",
            flag = "https://restcountries.eu/data/esp.svg",
            latlng = listOf(40.0, -4.0),
            name = "Spain",
            nativeName = "España",
            population = 46438422,
            area = 505992.0,
            region = "Europe",
            timezones = listOf("UTC", "UTC+01:00"),
        )

    }

    override suspend fun getAllCountries(): ObserverResponse<List<Country>> =
        ObserverResponse.Success(countriesList)

    override suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> =
        ObserverResponse.Success(countryDetails)
}