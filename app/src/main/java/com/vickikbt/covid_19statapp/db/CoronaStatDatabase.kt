package com.vickikbt.covid_19statapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vickikbt.covid_19statapp.data.CountriesCoronaData
import com.vickikbt.covid_19statapp.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData

@Database(
    entities = [GlobalCoronaData::class, CountriesCoronaDataEntry::class],
    version = 1
)
abstract class CoronaStatDatabase : RoomDatabase() {
    abstract fun globalCoronaStatDao(): GlobalCoronaStatDAO
    abstract fun countriesCoronaStatDao(): CountriesCoronaStatDAO

    companion object {
        @Volatile
        private var instance: CoronaStatDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CoronaStatDatabase::class.java,
                "CoronaStatDatabase.db"
            ).build()
    }

}