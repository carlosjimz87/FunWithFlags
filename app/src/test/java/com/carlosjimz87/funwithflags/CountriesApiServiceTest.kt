package com.carlosjimz87.funwithflags

import com.carlosjimz87.funwithflags.network.api.ApiManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesApiTest {

    @Test
    fun testGetAllCountries() = runBlockingTest {
        val response = ApiManager.retrofitService.getAllCountries()
        Assert.assertEquals(146, response.size)
    }
}