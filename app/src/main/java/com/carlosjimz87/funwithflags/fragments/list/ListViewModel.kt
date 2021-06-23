package com.carlosjimz87.funwithflags.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.funwithflags.domain.list.Country
import com.carlosjimz87.funwithflags.network.api.ApiManager
import com.carlosjimz87.funwithflags.network.api.CountriesApi
import com.carlosjimz87.funwithflags.network.models.CountryItem
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import kotlinx.coroutines.launch
import timber.log.Timber

enum class CountriesApiStatus { LOADING, ERROR, SUCCESS }

class ListViewModel(
    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl(),
) : ViewModel() {
    private val _status = MutableLiveData<CountriesApiStatus>()
    val status: LiveData<CountriesApiStatus> = _status

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        getCountriesList()
    }

    private fun handleResponse(response: ObserverResponse<List<CountryItem>>) {

        when (response) {
            is ObserverResponse.Loading -> {
                _status.value = CountriesApiStatus.LOADING
            }
            is ObserverResponse.Error -> {
                _status.value = CountriesApiStatus.ERROR
                val errorMsg = response.exception?.message + response.errorMessage
                _error.value = errorMsg
                Timber.tag("FUN_WITH_FLAGS").e(errorMsg);
            }
            is ObserverResponse.Success -> {
                _status.value = CountriesApiStatus.SUCCESS
                _countries.value = response.data.map {
                    Country(
                        name = it.name,
                        code = it.code,
                        flag = it.flag
                    )
                }
                Timber.tag("FUN_WITH_FLAGS")
                    .i("Retrieved (${response.data.size} countries successfully!");
                Timber.tag("FUN_WITH_FLAGS").i("Country: ${response.data}");
            }
        }
    }


    private fun getCountriesList() {
        Timber.i("Fetching countries")
        _status.value = CountriesApiStatus.LOADING
        viewModelScope.launch {
            handleResponse(countriesRepository.getAllCountries())
        }
    }
}
