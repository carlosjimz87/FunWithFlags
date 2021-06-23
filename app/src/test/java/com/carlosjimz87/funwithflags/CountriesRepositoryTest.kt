package com.carlosjimz87.funwithflags

import com.carlosjimz87.funwithflags.network.api.CountriesApiService
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryItem
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
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
    private lateinit var apiServiceMock: CountriesApiService


    val mockCountriesResponse = listOf(
        CountryItem("Argentina", "ARG", "https://restcountries.eu/data/arg.svg"),
        CountryItem("Brazil", "BRA", "https://restcountries.eu/data/bra.svg"),
        CountryItem("Canada", "CAN", "https://restcountries.eu/data/can.svg"),
        CountryItem("Colombia", "COL", "https://restcountries.eu/data/col.svg"),
        CountryItem("France", "FRA", "https://restcountries.eu/data/fra.svg"),
        CountryItem("Spain", "ESP", "https://restcountries.eu/data/esp.svg"),
        CountryItem("Great Britain", "GBR", "https://restcountries.eu/data/gbr.svg"),
        CountryItem("United States", "USA", "https://restcountries.eu/data/usa.svg"),
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        countriesRepository = object : CountriesRepositoryImpl(apiServiceMock) {

            override suspend fun getAllCountries(): ObserverResponse<List<CountryItem>> {
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