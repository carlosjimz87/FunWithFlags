package com.carlosjimz87.funwithflags.network.models

import android.graphics.drawable.Drawable

data class CountryProps(
    var name: String? = "",
    var code: String? = "",
    val nativeName: Pair<String,String>?,
    val capital: Pair<String,String>?,
    val population: Pair<String,String>?,
    val demonym: Pair<String,String>?,
    val callingCode: Pair<String,String>?,
    val currency: Pair<String,String>?,
    val timezone: Pair<String,String>?,
    val region: Drawable?,
)