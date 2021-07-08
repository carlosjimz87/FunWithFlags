package com.carlosjimz87.funwithflags.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var fakeCountriesApi: FakeCountriesApi
    private lateinit var countriesService: CountriesServiceImpl

    // Class under test
    private lateinit var countriesRepository: CountriesRepository

    @Before
    fun setup() {
        fakeCountriesApi = FakeCountriesApi()
        countriesService = CountriesServiceImpl(fakeCountriesApi)
        countriesRepository = CountriesRepositoryImpl(countriesService)
    }


    @Test
    fun testGetAllCountries() = runBlocking {
        val response = countriesRepository.getAllCountries()
        Assert.assertEquals(true, response is ObserverResponse.Success)
        assertThat(response.data, IsEqual(FakeCountriesApi.countriesList))
    }
}