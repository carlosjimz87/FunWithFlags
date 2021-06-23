package com.carlosjimz87.funwithflags.network.responses

sealed class ObserverResponse<T>(
    open val data: T? = null,
    open val errorMessage: String? = null,
    open val exception: Exception? = null,
) {
    data class Success<T>(override val data: T) : ObserverResponse<T>(data)

    data class Error<T>(
        override val errorMessage: String? = null,
        override val exception: Exception? = null,
    ) : ObserverResponse<T>()

    class Loading<T> : ObserverResponse<T>()
}