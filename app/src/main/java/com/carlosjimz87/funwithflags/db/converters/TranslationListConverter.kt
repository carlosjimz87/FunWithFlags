package com.carlosjimz87.funwithflags.db.converters

import androidx.room.TypeConverter
import com.carlosjimz87.funwithflags.network.models.Translations
import com.google.gson.Gson

class TranslationListConverter {
    companion object {

        @TypeConverter
        fun toJson(data: Translations): String = Gson().toJson(data)

        @TypeConverter
        fun fromJson(data: String): Translations = Gson().fromJson(data, Translations::class.java)
    }
}
