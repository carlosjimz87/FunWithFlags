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
    override suspend fun getAllCountries(): Flow<ObserverResponse<List<Country>>> {

        return withContext(Dispatchers.Main){
            flow {
                getCachedCountries()?.let {
                    emit(ObserverResponse.Success(it))
                }
                val response = getNetworkCountries()
                response.data?.let {
                    updateDataBase(it)
                }
                emit(response)
            }
        }
    }
    override suspend fun getCountryDetails(countryId: String): Flow<ObserverResponse<CountryDetails>> {
        return service.getCountryDetails(countryId)
    }

    // Network Accesses

    private suspend fun getNetworkCountries() = service.getAllCountries()

    // Database Accesses

    private fun getCachedCountries() = database?.countries()?.getAll()

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