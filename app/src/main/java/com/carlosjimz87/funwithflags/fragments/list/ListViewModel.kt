package com.carlosjimz87.funwithflags.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.funwithflags.fragments.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import com.carlosjimz87.funwithflags.utils.handleResponse
import kotlinx.coroutines.launch
import timber.log.Timber

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
        _status.value = CountriesApiStatus.SUCCESS
        getCountriesList()
    }

    private fun getCountriesList() {
        Timber.i("Fetching countries")
        _status.value = CountriesApiStatus.LOADING
        viewModelScope.launch {
            val response = countriesRepository.getAllCountries()
            var data = handleResponse(response, _status, _error)
            data = data?.filter {
                it.name.length <= 30
            }
            data?.let {
                _countries.value = it
                Timber.tag("FUN_WITH_FLAGS")
                    .i("Retrieved (${it.size} countries successfully!");
                Timber.tag("FUN_WITH_FLAGS").i("Country: $it");
            }
        }
    }


}
