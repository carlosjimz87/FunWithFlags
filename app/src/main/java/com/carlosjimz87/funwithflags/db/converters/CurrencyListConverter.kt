package com.carlosjimz87.funwithflags.db.converters

import androidx.room.TypeConverter
import com.carlosjimz87.funwithflags.network.models.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CurrencyListConverter {
    companion object {
        private val CurrencyListType: Type? = object : TypeToken<List<Currency>>() {}.type

        @TypeConverter
        fun toJson(data: List<Currency>): String = Gson().toJson(data)

        @TypeConverter
        fun fromJson(data: String): List<Currency> = Gson().fromJson(data, CurrencyListType)
    }
}
