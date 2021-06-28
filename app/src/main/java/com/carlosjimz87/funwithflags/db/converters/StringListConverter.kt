package com.carlosjimz87.funwithflags.db.converters

import androidx.room.TypeConverter

class StringListConverter {

    companion object {
        @TypeConverter
        fun toList(data: String): List<String> = data.split(",")

        @TypeConverter
        fun fromList(data: List<String>) = data.joinToString()
    }
}
