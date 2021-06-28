package com.carlosjimz87.funwithflags.db.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.carlosjimz87.funwithflags.db.converters.CurrencyListConverter
import com.carlosjimz87.funwithflags.db.converters.DoubleListConverter
import com.carlosjimz87.funwithflags.db.converters.StringListConverter
import com.carlosjimz87.funwithflags.db.converters.TranslationListConverter
import com.carlosjimz87.funwithflags.network.models.Currency
import com.carlosjimz87.funwithflags.network.models.Translations

@Entity(tableName = "countries")
data class CountryDisk(
    @PrimaryKey
    @NonNull val alpha3Code: String,

    @TypeConverters(StringListConverter::class)
    @NonNull val callingCodes: List<String>,

    @NonNull val capital: String,

    @TypeConverters(CurrencyListConverter::class)
    @NonNull val currencies: List<Currency>,

    @NonNull val flag: String,

    @TypeConverters(DoubleListConverter::class)
    @NonNull val latlng: List<Double>,

    @NonNull val name: String,

    val nativeName: String,
    val demonym: String,
    val population: Long,
    val region: String,

    @TypeConverters(StringListConverter::class)
    @NonNull val timezones: List<String>,

    @TypeConverters(TranslationListConverter::class)
    val translations: Translations,
)
