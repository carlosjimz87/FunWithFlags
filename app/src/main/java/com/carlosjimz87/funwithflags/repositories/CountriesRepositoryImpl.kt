package com.carlosjimz87.funwithflags.repositories

import android.content.Context
import com.carlosjimz87.funwithflags.App
import com.carlosjimz87.funwithflags.db.CountriesDatabase
import com.carlosjimz87.funwithflags.network.api.ApiManager
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.network.models.CountryDetails
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import com.carlosjimz87.funwithflags.network.services.CountriesServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

open class CountriesRepositoryImpl(
    private val database: CountriesDatabase? = null,
    private val service: CountriesServiceImpl = CountriesServiceImpl(ApiManager.retrofitService),
) : CountriesRepository {

    override suspend fun getAllCountries(): ObserverResponse<List<Country>> {

        return withContext(Dispatchers.Main) {
            when (val response = getNetworkCountries()) {
                is ObserverResponse.Success -> {
                    updateDataBase(response.data)
                }
                is ObserverResponse.Error -> {
                    val cachedCountries = getCachedCountries()
                    if (cachedCountries.i)
                }
            }
//                getCachedCountries()?.let {
//                    emit(ObserverResponse.Success(it))
//                }
//                val response = getNetworkCountries()
//                response.data?.let {
//                    updateDataBase(it)
//                }
//                emit(response)
        }
    }


    override suspend fun getCountryDetails(countryId: String): Flow<ObserverResponse<CountryDetails>> {
        return service.getCountryDetails(countryId)
    }

// Network Accesses

    private suspend fun getNetworkCountries() = service.getAllCountries()

// Database Accesses

    private fun getCachedCountries(): Flow<ObserverResponse<List<Country>>> = {
            database?.countries().getAll()?.let {

            }
    }

    private fun updateDataBase(countries: List<Country>?) {
        countries?.let {
            val mapperToTicketDisk = MapperToTicketDisk()
            val ticketDiskList: MutableList<RealmObject> = ArrayList()
            it.forEach { ticket: Ticket ->
                ticketDiskList.add(mapperToTicketDisk.map(ticket))
            }
            dbManager.put(ticketDiskList)
        }
    }
}
}