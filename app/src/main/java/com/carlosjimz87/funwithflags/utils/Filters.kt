package com.carlosjimz87.funwithflags.utils

import com.carlosjimz87.funwithflags.network.models.Country

fun filterListCountry(regex: CharSequence, list: MutableList<Country>): MutableList<Country> =
    list.filter {
        nameAndCodeCriteria(it, regex)
    }.toMutableList()


fun codeCriteria(
    country: Country,
    regex: CharSequence,
): Boolean {

    val filterPattern = regex.toString().lowercase().trim()
    val code = country.code.lowercase().replace(" ", "").trim()

    return code.startsWith(filterPattern, false)
}

fun nameAndCodeCriteria(
    country: Country,
    regex: CharSequence,
): Boolean {

    val filterPattern = regex.toString().lowercase().trim()
    val code = country.code.lowercase().replace(" ", "").trim()
    val name = country.name.lowercase().replace(" ", "").trim()

    return code.startsWith(filterPattern, false)
            || name.contains(filterPattern, false)
}
