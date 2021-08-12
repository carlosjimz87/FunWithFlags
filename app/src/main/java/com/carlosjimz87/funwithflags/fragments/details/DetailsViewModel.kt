package com.carlosjimz87.funwithflags.fragments.details

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.adapters.getTranslatedName
import com.carlosjimz87.funwithflags.fragments.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.models.CountryProps
import com.carlosjimz87.funwithflags.network.models.Currency
import com.carlosjimz87.funwithflags.repositories.CountriesRepository
import com.carlosjimz87.funwithflags.utils.*
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel @ViewModelInject constructor(
    private val countriesRepository: CountriesRepository, application: Application,
) : AndroidViewModel(application) {

    private val _status = MutableLiveData<CountriesApiStatus>()
    val status: LiveData<CountriesApiStatus> = _status

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _shareDetails = MutableLiveData<Pair<String, String>>()
    val shareDetails: LiveData<Pair<String, String>> = _shareDetails

    private val _countryDetails = MutableLiveData<CountryDetails>()
    val countryDetails: LiveData<CountryDetails> = _countryDetails

    private val _countryProps = MutableLiveData<CountryProps>()
    val countryProps: LiveData<CountryProps> = _countryProps

    private val _position = MutableLiveData<Pair<String, List<Double>>>()
    val position: LiveData<Pair<String, List<Double>>> = _position


    init {
        _status.value = CountriesApiStatus.SUCCESS
    }

    fun getCountryDetails(code: String) {
        Timber.i("Fetching country details")
        if (code.isNotEmpty()) {
            _status.value = CountriesApiStatus.LOADING
            viewModelScope.launch {
                val response = countriesRepository.getCountryDetails(code)
                val data = handleResponse(response, _status, _error)
                data?.let {
                    _countryDetails.value = it.copy(name = getTranslatedName(it, getApplication()))
                    _countryProps.value = getCountryProps(it)
                    _position.value = Pair(it.name, it.latlng)
                    Timber.tag("FUN_WITH_FLAGS")
                        .i("Retrieved country (${it.alpha3Code}) successfully!");
                    Timber.tag("FUN_WITH_FLAGS").i("Country: $it");
                }
            }
        }
    }

    private fun getCountryProps(country: CountryDetails): CountryProps {
        with(country) {
            val countryProps = CountryProps(
                capital = getCapital(getApplication(), capital),
                nativeName = getNativeName(getApplication(), nativeName),
                demonym = getDemonym(getApplication(), demonym.toString()),
                population = getPopulation(getApplication(), population),
                callingCode = getCallingCodes(getApplication(), callingCodes),
                currency = getCurrencies(getApplication(), currencies),
                timezone = getTimezones(getApplication(), timezones),
                region = getRegion(getApplication(), region)
            )

            _shareDetails.value = Pair(
                formatShareTitle(country.name, country.alpha3Code, getApplication()),
                formatShareText(countryProps, getApplication())
            )

            return countryProps
        }
    }

    private val regionsMap = mapOf(
        "Africa" to R.drawable.ic_africa,
        "Europe" to R.drawable.ic_europe,
        "Americas" to R.drawable.ic_america,
        "Asia" to R.drawable.ic_asia,
        "Polar" to R.drawable.ic_antarctica,
        "Oceania" to R.drawable.ic_oceania,
    )

    private fun getCapital(context: Context, capital: String): Pair<String, String> {
        return Pair(context.resources.getString(R.string.capital_prefix), capital)
    }

    private fun getDemonym(context: Context, demonym: String): Pair<String, String> {
        return Pair(context.resources.getString(R.string.demonym_prefix), demonym)
    }

    private fun getRegion(context: Context, region: String?): Drawable? {
        return region?.let {
            return regionsMap[region]?.let { it1 -> context.getCompatDrawable(it1) }
        }
    }

    private fun getNativeName(context: Context, nativeName: String?): Pair<String, String>? {
        return nativeName?.let {
            return Pair(
                first = context.resources.getString(R.string.native_prefix),
                second = nativeName
            )
        }
    }

    private fun getPopulation(context: Context, population: Long?): Pair<String, String>? {
        return population?.let {
            return Pair(
                first = context.resources.getString(R.string.population_prefix),
                second = formatPopulation(population, context)
            )
        }
    }


    private fun getTimezones(context: Context, timezones: List<String>): Pair<String, String>? {
        return if (timezones.isNotEmpty()) Pair(
            context.resources.getString(R.string.timezone_prefix),
            "+${timezones[0]}"
        ) else null
    }

    private fun getCallingCodes(context: Context, codes: List<String>): Pair<String, String>? {
        return if (codes.isNotEmpty() && codes[0].isNotEmpty()) Pair(
            context.resources.getString(
                R.string.calling_code_prefix
            ),
            "+${codes[0]}"
        ) else null
    }

    private fun getCurrencies(context: Context, currencies: List<Currency>): Pair<String, String>? {
        if (currencies.isNotEmpty()) {
            val currency = currencies[0]
            currency.code?.let {
                return formatCurrency(currency.code, currency.symbol, context)
            }
        }
        return null
    }
}