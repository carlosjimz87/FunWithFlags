package com.carlosjimz87.funwithflags.network

import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CountriesRepositoryTest {

    private lateinit var countriesRepository: CountriesRepository

    @Mock
    private lateinit var apiServiceMock: CountriesApi


    val mockCountriesResponse = listOf(
        Country("Argentina", "ARG", "https://restcountries.eu/data/arg.svg"),
        Country("Brazil", "BRA", "https://restcountries.eu/data/bra.svg"),
        Country("Canada", "CAN", "https://restcountries.eu/data/can.svg"),
        Country("Colombia", "COL", "https://restcountries.eu/data/col.svg"),
        Country("France", "FRA", "https://restcountries.eu/data/fra.svg"),
        Country("Spain", "ESP", "https://restcountries.eu/data/esp.svg"),
        Country("Great Britain", "GBR", "https://restcountries.eu/data/gbr.svg"),
        Country("United States", "USA", "https://restcountries.eu/data/usa.svg"),
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val service = CountriesServiceImpl(apiServiceMock)
        countriesRepository = object : CountriesRepositoryImpl(service) {

            override suspend fun getAllCountries(): ObserverResponse<List<Country>> {
                return ObserverResponse.Success(data = mockCountriesResponse)
            }

            override suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> {
                TODO("Not yet implemented")
            }
        }
    }


    @Test
    fun testGetAllCountries() = runBlockingTest {
        val response = countriesRepository.getAllCountries()
        Assert.assertEquals(true, response is ObserverResponse.Success)
        Assert.assertEquals(mockCountriesResponse.size, response.data?.size)
    }
}