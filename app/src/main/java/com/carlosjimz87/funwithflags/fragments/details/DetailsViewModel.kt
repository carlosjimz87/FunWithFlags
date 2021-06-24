package com.carlosjimz87.funwithflags.fragments.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.fragments.list.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryProps
import com.carlosjimz87.funwithflags.network.models.Currency
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.repositories.CountriesRepositoryImpl
import com.carlosjimz87.funwithflags.utils.handleResponse
import com.carlosjimz87.funwithflags.utils.justify
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(
    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl(),
) : ViewModel() {
    private lateinit var context: Context

    private val _status = MutableLiveData<CountriesApiStatus>()
    val status: LiveData<CountriesApiStatus> = _status

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _countryDetails = MutableLiveData<CountryDetails>()
    val countryDetails: LiveData<CountryDetails> = _countryDetails

    private val _countryProps = MutableLiveData<CountryProps>()
    val countryProps: LiveData<CountryProps> = _countryProps

    fun setContext(context: Context) {
        this.context = context
    }

    fun getCountryDetails(code: String) {
        Timber.i("Fetching country details")
        if (code.isNotEmpty()) {
            _status.value = CountriesApiStatus.LOADING
            viewModelScope.launch {
                val response = countriesRepository.getCountryDetails(code)
                val data = handleResponse(response, _status, _error)
                data?.let {
                    _countryDetails.value = it.copy(name = it.name.justify())
                    _countryProps.value = getCountryProps(it)
                    Timber.tag("FUN_WITH_FLAGS")
                        .i("Retrieved country (${it.alpha3Code}) successfully!");
                    Timber.tag("FUN_WITH_FLAGS").i("Country: $it");
                }
            }
        }
    }

    private fun getCountryProps(country: CountryDetails): CountryProps {

        val props = CountryProps(
            capital = Pair(first = context.getString(R.string.capital_prefix),
                second = country.capital),
            population = Pair(first = context.getString(R.string.population_prefix),
                second = country.population.toString()),
            demonym = Pair(first = context.getString(R.string.demonym_prefix),
                second = country.demonym.toString()),
            callingCode = getCallingCodes(country.callingCodes),
            currency = getCurrencies(country.currencies),
        )
        return props
    }

    private fun getCallingCodes(codes: List<String>): Pair<String, String>? {
        return if (codes.isNotEmpty()) Pair(context.getString(R.string.calling_code_prefix),
            "+${codes[0]}") else null
    }

    private fun getCurrencies(currencies: List<Currency>): Pair<String, String>? {
        if (currencies.isNotEmpty()) {
            val currency = currencies[0]
            currency.symbol?.let {
                return Pair(context.getString(R.string.currency_prefix), "${currency.code} ($it)")
            }
            return Pair(context.getString(R.string.currency_prefix), "${currency.code}")
        } else return null
    }
}