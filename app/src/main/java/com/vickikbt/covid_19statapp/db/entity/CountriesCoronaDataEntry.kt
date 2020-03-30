package com.vickikbt.covid_19statapp.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vickikbt.covid_19statapp.data.CountryInfo

@Entity(tableName = "countries_statistics", indices = [Index(value = ["country"])])
data class CountriesCoronaDataEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Int,
    val country: String,
    @Embedded(prefix = "countryInfo_")
    val countryInfo: CountryInfo,
    val critical: Int,
    val deaths: Int,
    val deathsPerOneMillion: Int,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int
)