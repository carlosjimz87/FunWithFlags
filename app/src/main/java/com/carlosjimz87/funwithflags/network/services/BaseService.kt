package com.carlosjimz87.funwithflags.network.services

import com.carlosjimz87.funwithflags.network.responses.ObserverResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BaseService {

    suspend fun <T> apiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): ObserverResponse<T> {
        return withContext(dispatcher) {
            try {
                ObserverResponse.Success(apiCall.invoke())
            } catch (exception: Exception) {
                ObserverResponse.Error(exception = exception, errorMessage = exception.message)
            }
        }
    }
}