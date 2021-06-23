package com.carlosjimz87.funwithflags.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosjimz87.funwithflags.domain.list.Country

enum class CountriesApiStatus { LOADING, ERROR, DONE }

class ListViewModel : ViewModel() {
    private val _status = MutableLiveData<CountriesApiStatus>()
    val status: LiveData<CountriesApiStatus> = _status

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        getCountries()
    }

    private fun getCountries() {
        _status.value = CountriesApiStatus.LOADING
        val listCountries = listOf(
            Country("Argentina", "ARG", "https://restcountries.eu/data/arg.svg"),
            Country("Brazil", "BRA", "https://restcountries.eu/data/bra.svg"),
            Country("Canada", "CAN", "https://restcountries.eu/data/can.svg"),
            Country("Colombia", "COL", "https://restcountries.eu/data/col.svg"),
            Country("France", "FRA", "https://restcountries.eu/data/fra.svg"),
            Country("Spain", "ESP", "https://restcountries.eu/data/esp.svg"),
            Country("Great Britain", "GBR", "https://restcountries.eu/data/gbr.svg"),
            Country("United States", "USA", "https://restcountries.eu/data/usa.svg"),
        )
        _countries.value = listCountries
        _status.value = CountriesApiStatus.DONE
    }
}
