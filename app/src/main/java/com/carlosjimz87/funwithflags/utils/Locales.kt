package com.carlosjimz87.funwithflags.utils

import android.content.Context
import java.util.Locale

fun getLocale(context: Context): String = context.resources.configuration.locale.language

fun setLocale(context: Context, lang: String) =
    context.resources.configuration.setLocale(Locale(lang))
