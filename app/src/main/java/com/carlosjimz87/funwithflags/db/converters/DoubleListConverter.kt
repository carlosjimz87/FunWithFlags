package com.carlosjimz87.funwithflags.db.converters

import androidx.room.TypeConverter

class DoubleListConverter {

    companion object {
        @TypeConverter
        fun toList(data: String): List<Double> = data.split(",").map { it.toDouble() }

        @TypeConverter
        fun fromList(data: List<Double>) = data.joinToString()
    }
}
