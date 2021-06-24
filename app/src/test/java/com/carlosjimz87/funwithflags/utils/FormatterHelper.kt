package com.carlosjimz87.funwithflags.utils

import kotlin.math.ln
import kotlin.math.pow


fun formatPopulationHelper(population: Long): String {
    if (population < 1000) return population.toString()

    val exp = (ln(population.toDouble()) / ln(1000.0)).toInt();

    val suffixs = listOf("k", "M", "B", "T", "P", "E")

    return String.format("%.1f %s",
        population / 1000.0.pow(exp.toDouble()),
        suffixs[exp - 1]);
}

fun formatCurrencyHelper(code: String, symbol: String? = null): Pair<String, String> {
    symbol?.let {
        return Pair("Currency", "$code ($it)")
    }
    return Pair("Currency", code)
}