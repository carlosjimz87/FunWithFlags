package com.carlosjimz87.funwithflags.fragments.details

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.carlosjimz87.funwithflags.App
import com.carlosjimz87.funwithflags.CoroutineTestRule
import com.carlosjimz87.funwithflags.network.FakeCountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.utils.formatCurrencyHelper
import com.carlosjimz87.funwithflags.utils.formatPopulationHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var fakeCountriesRepository: CountriesRepository

    // Class under test
    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setup() {
        fakeCountriesRepository = FakeCountriesRepository()
        detailsViewModel = DetailsViewModel(appContext as App, fakeCountriesRepository)
    }

    @Test
    fun getCountryDetailsTest(): Unit = runBlocking {

        detailsViewModel.countryDetails.observeForever {
            Assert.assertEquals(FakeCountriesRepository.countryDetails.name, it.name)
            Assert.assertEquals(FakeCountriesRepository.countryDetails.flag, it.flag)
        }
        detailsViewModel.countryProps.observeForever {

            Assert.assertEquals(formatPopulationHelper(FakeCountriesRepository.countryDetails.population!!),
                it.population)
            Assert.assertEquals(formatCurrencyHelper(FakeCountriesRepository.countryDetails.currencies[0].code!!),
                it.currency)
        }
    }
}