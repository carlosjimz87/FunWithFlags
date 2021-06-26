package com.carlosjimz87.funwithflags.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.fragments.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.responses.ObserverResponse

fun RecyclerView.addDividerShape(context: Context, resource: Int): RecyclerView {
    val dividerItemDecoration = DividerItemDecoration(context,
        DividerItemDecoration.VERTICAL)

    val dividerShape: Drawable = ResourcesCompat.getDrawable(context.resources,
        resource,
        null)!!

    dividerItemDecoration.setDrawable(dividerShape)
    this.addItemDecoration(dividerItemDecoration)
    return this
}

private const val MAX_LENGTH_NAME = 20

fun String.justify(): String {
    if (this.length >= MAX_LENGTH_NAME) {
        var splitNames = this.split(" ")

        splitNames = splitNames.mapIndexed { index, s ->
            if (index == splitNames.size / 2 - 1) s.plus("\n") else s.plus(" ")
        }
        return splitNames
            .joinToString(separator = "") {
                it
            }.trim()
    }
    return this
}

inline fun <reified T> handleResponse(
    response: ObserverResponse<T>,
    status: MutableLiveData<CountriesApiStatus>,
    error: MutableLiveData<String>,
): T? {

    return when (response) {
        is ObserverResponse.Loading -> {
            status.value = CountriesApiStatus.LOADING
            response.data
        }
        is ObserverResponse.Error -> {
            status.value = CountriesApiStatus.ERROR
            error.value = response.exception?.message + response.errorMessage
            response.data
        }
        is ObserverResponse.Success -> {
            status.value = CountriesApiStatus.SUCCESS
            response.data
        }
    }
}


fun Context.getCompatDrawable(id: Int, theme: Resources.Theme? = null): Drawable? {
    return ResourcesCompat.getDrawable(this.resources, id, theme)
}