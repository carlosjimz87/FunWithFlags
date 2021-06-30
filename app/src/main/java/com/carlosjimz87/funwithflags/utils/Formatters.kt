package com.carlosjimz87.funwithflags.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.network.models.CountryProps
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

fun formatShareText(countryProps: CountryProps, context: Context): String {
    return with(countryProps) {
        context.getString(R.string.country_details_text,
            capital?.first, capital?.second,
            population?.first, population?.second,
            demonym?.first, demonym?.second,
            currency?.first, currency?.second,
            nativeName?.first, demonym?.second,
            timezone?.first, timezone?.second
        )
    }
}

fun String.toHtml(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString();
    }


fun formatShareTitle(name: String, code: String, context: Context): String =
    context.getString(R.string.country_details_title,
        name,
        code
    )
