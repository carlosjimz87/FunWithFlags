package com.carlosjimz87.funwithflags.fragments.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carlosjimz87.funwithflags.fragments.details.DetailsViewModel
import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.models.*
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import com.carlosjimz87.funwithflags.utils.formatCurrencyHelper
import com.carlosjimz87.funwithflags.utils.formatPopulationHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class DetailsViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockCountriesRepository: CountriesRepository

    @Mock
    private lateinit var apiServiceMock: CountriesApi


    private val mockCountryDetails = CountryDetails(
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

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        val service = CountriesServiceImpl(apiServiceMock)
        mockCountriesRepository = object : CountriesRepositoryImpl(service) {

            override suspend fun getAllCountries(): ObserverResponse<List<Country>> {
                TODO("Not yet implemented")
            }

            override suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> {
                return ObserverResponse.Success(data = mockCountryDetails)
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getCountryDetailsTest(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val viewModel = DetailsViewModel(mockCountriesRepository)

            viewModel.countryDetails.observeForever {
                Assert.assertEquals(mockCountryDetails.name, it.name)
                Assert.assertEquals(mockCountryDetails.flag, it.flag)
            }
            viewModel.countryProps.observeForever {

                Assert.assertEquals(formatPopulationHelper(mockCountryDetails.population!!), it.population)
                Assert.assertEquals(formatCurrencyHelper(mockCountryDetails.currencies[0].code!!), it.currency)
            }
        }
    }
}