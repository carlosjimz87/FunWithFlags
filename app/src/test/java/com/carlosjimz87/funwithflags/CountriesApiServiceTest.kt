package com.carlosjimz87.funwithflags

import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesApiTest {

    @Test
    fun testGetAllCountries() = runBlockingTest {
        val response = CountriesApi.retrofitService.getAllCountries()
        Assert.assertEquals(true, response is ObserverResponse.Success)
        Assert.assertEquals(146, response.data?.size)
    }
}