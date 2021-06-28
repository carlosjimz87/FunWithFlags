package com.carlosjimz87.funwithflags.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.carlosjimz87.funwithflags.db.converters.StringListConverter
import com.carlosjimz87.funwithflags.network.models.Currency
import com.carlosjimz87.funwithflags.network.models.Translations

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    val alpha3Code: String,
    val area: Double?,
    @TypeConverters(StringListConverter::class)
    val callingCodes: List<String>,
    val capital: String,
    val currencies: List<Currency>,
    val demonym: String?,
    val flag: String,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String?,
    val population: Long?,
    val region: String?,
    @TypeConverters(StringListConverter::class)
    val timezones: List<String>,
    val translations: Translations? = null,
)
