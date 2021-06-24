package com.carlosjimz87.funwithflags.network.models

data class CountryProps(
    val capital: Pair<String,String>?,
    val population: Pair<String,String>?,
    val demonym: Pair<String,String>?,
    val callingCode: Pair<String,String>?,
    val currency: Pair<String,String>?,
)