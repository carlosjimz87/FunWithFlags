package com.carlosjimz87.funwithflags

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carlosjimz87.funwithflags.fragments.list.ListViewModel
import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryItem
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class ListViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockCountriesRepository: CountriesRepository

    @Mock
    private lateinit var apiServiceMock: CountriesApi


    private val mockCountriesResponse = listOf(
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
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        val service = CountriesServiceImpl(apiServiceMock)
        mockCountriesRepository = object : CountriesRepositoryImpl(service) {

            override suspend fun getAllCountries(): ObserverResponse<List<CountryItem>> {
                return ObserverResponse.Success(data = mockCountriesResponse)
            }

            override suspend fun getCountryDetails(countryId: String): ObserverResponse<CountryDetails> {
                TODO("Not yet implemented")
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getAllCountries(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val viewModel = ListViewModel(mockCountriesRepository)

            viewModel.countries.observeForever {
                Assert.assertEquals(mockCountriesResponse.size, it.size)
            }
        }
    }
}