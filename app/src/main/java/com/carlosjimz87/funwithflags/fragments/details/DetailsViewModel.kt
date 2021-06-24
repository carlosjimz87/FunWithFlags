package com.carlosjimz87.funwithflags.fragments.details

import android.content.Context
import android.graphics.drawable.Drawable
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
import com.carlosjimz87.funwithflags.utils.getCompatDrawable
import com.carlosjimz87.funwithflags.utils.handleResponse
import com.carlosjimz87.funwithflags.utils.justify
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.ln
import kotlin.math.pow

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
        with(country) {
            return CountryProps(
                capital = Pair(first = context.getString(R.string.capital_prefix),
                    second = capital),
                nativeName = getNativeName(nativeName),
                demonym = Pair(first = context.getString(R.string.demonym_prefix),
                    second = demonym.toString()),
                population = getPopulation(population),
                callingCode = getCallingCodes(callingCodes),
                currency = getCurrencies(currencies),
                timezone = getTimezones(timezones),
                region = getRegion(region)
            )
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

    private fun getRegion(region: String?): Drawable? {
        return region?.let {
            return regionsMap[region]?.let { it1 -> context.getCompatDrawable(it1) }
        }
    }

    private fun getNativeName(nativeName: String?): Pair<String, String>? {
        return nativeName?.let {
            return Pair(first = context.getString(R.string.native_prefix),
                second = nativeName)
        }
    }

    private fun getPopulation(population: Long?): Pair<String, String>? {
        return population?.let {
            return Pair(first = context.getString(R.string.population_prefix),
                second = formatPopulation(population))
        }
    }

    private fun formatPopulation(population: Long): String {
        if (population < 1000) return population.toString()

        val exp = (ln(population.toDouble()) / ln(1000.0)).toInt();
        return String.format("%.1f %c",
            population / 1000.0.pow(exp.toDouble()),
            "kMBTPE".toCharArray()[exp - 1]);
    }

    private fun getTimezones(timezones: List<String>): Pair<String, String>? {
        return if (timezones.isNotEmpty()) Pair(context.getString(R.string.timezone_prefix),
            "+${timezones[0]}") else null
    }

    private fun getCallingCodes(codes: List<String>): Pair<String, String>? {
        return if (codes.isNotEmpty()) Pair(context.getString(R.string.calling_code_prefix),
            "+${codes[0]}") else null
    }

    private fun getCurrencies(currencies: List<Currency>): Pair<String, String>? {
        if (currencies.isNotEmpty()) {
            val currency = currencies[0]
            currency.code?.let {
                return formatCurrency(currency.code, currency.symbol)
            }
        }
        return null
    }

    private fun formatCurrency(code: String, symbol: String? = null): Pair<String, String> {
        symbol?.let {
            return Pair(context.getString(R.string.currency_prefix), "$code ($it)")
        }
        return Pair(context.getString(R.string.currency_prefix), code)
    }
}