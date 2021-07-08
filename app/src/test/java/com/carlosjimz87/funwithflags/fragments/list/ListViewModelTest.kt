package com.carlosjimz87.funwithflags.fragments.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carlosjimz87.funwithflags.CoroutineTestRule
import com.carlosjimz87.funwithflags.network.FakeCountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var fakeCountriesRepository: CountriesRepository

    // Class under test
    private lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {
        fakeCountriesRepository = FakeCountriesRepository()
        listViewModel = ListViewModel(fakeCountriesRepository)
    }

    @Test
    fun getAllCountriesTest() {

        coroutinesTestRule.testDispatcher.runBlockingTest {
            listViewModel.countries.observeForever {
                Assert.assertEquals(FakeCountriesRepository.countriesList.size, it.size)
            }
        }
    }
}