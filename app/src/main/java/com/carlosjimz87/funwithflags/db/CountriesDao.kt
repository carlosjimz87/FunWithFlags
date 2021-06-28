package com.carlosjimz87.funwithflags.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlosjimz87.funwithflags.db.models.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries ORDER BY alpha3Code")
    fun getAll(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<CountryEntity>)

    @Query("DELETE FROM countries")
    fun deleteAll()

    @Query("SELECT * FROM countries WHERE alpha3Code = :code ORDER BY alpha3Code")
    fun getByCode(code: String): Flow<CountryEntity>
}
