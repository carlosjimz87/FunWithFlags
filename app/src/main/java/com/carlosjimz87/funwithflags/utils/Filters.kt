package com.carlosjimz87.funwithflags.utils

import com.carlosjimz87.funwithflags.network.models.Country
import java.util.*

fun filterListCountry(regex: CharSequence, list: MutableList<Country>): MutableList<Country> =
    list.filter {
        criteria(it.code, regex)
    }.toMutableList()


fun criteria(
    str: String,
    regex: CharSequence,
): Boolean {
    val filterPattern = regex.toString().lowercase(Locale.getDefault()).trim()
    return str.lowercase(Locale.getDefault()).startsWith(filterPattern, false)
}


