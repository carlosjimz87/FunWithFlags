package com.carlosjimz87.funwithflags.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlosjimz87.funwithflags.db.models.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countries(): CountriesDao

    companion object {
        @Volatile
        private var INSTANCE: CountriesDatabase? = null

        fun getDatabase(context: Context): CountriesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CountriesDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/countries.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
