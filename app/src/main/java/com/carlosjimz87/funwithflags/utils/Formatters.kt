package com.carlosjimz87.funwithflags.utils

import android.content.Context
import com.carlosjimz87.funwithflags.R
import kotlin.math.ln
import kotlin.math.pow

fun formatPopulation(population: Long, context: Context): String {
    if (population < 1000) return population.toString()

    val exp = (ln(population.toDouble()) / ln(1000.0)).toInt();

    val suffixs = context.resources.getStringArray(R.array.population_suffixes)

    return String.format("%.1f %s",
        population / 1000.0.pow(exp.toDouble()),
        suffixs[exp - 1]);
}

fun formatCurrency(code: String, symbol: String? = null, context: Context): Pair<String, String> {
    symbol?.let {
        return Pair(context.getString(R.string.currency_prefix), "$code ($it)")
    }
    return Pair(context.getString(R.string.currency_prefix), code)
}
