package com.carlosjimz87.funwithflags.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.funwithflags.fragments.list.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import com.carlosjimz87.funwithflags.utils.handleResponse
import com.carlosjimz87.funwithflags.utils.justify
import kotlinx.coroutines.launch
import timber.log.Timber

enum class CountriesApiStatus { LOADING, ERROR, SUCCESS }

class DetailsViewModel(
    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl(),
) : ViewModel() {

    private val _status = MutableLiveData<CountriesApiStatus>()
    val status: LiveData<CountriesApiStatus> = _status

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _countryDetails = MutableLiveData<CountryDetails>()
    val countryDetails: LiveData<CountryDetails> = _countryDetails

    fun getCountryDetails(code: String) {
        Timber.i("Fetching country details")
        if (code.isNotEmpty()) {
            _status.value = CountriesApiStatus.LOADING
            viewModelScope.launch {
                val response = countriesRepository.getCountryDetails(code)
                val data = handleResponse(response, _status, _error)
                data?.let {
                    _countryDetails.value = it.copy(name = it.name.justify())
                    Timber.tag("FUN_WITH_FLAGS")
                        .i("Retrieved country (${it.alpha3Code}) successfully!");
                    Timber.tag("FUN_WITH_FLAGS").i("Country: $it");
                }
            }
        }
    }
}