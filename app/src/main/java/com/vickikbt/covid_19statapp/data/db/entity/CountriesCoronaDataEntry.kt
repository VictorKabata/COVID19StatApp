package com.vickikbt.covid_19statapp.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_statistics")
data class CountriesCoronaDataEntry(
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Float,
    @PrimaryKey(autoGenerate = false)
    val country: String,
    val critical: Int,
    val deaths: Int,
    val deathsPerOneMillion: Float,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int

    /*@Embedded(prefix = "countryInfo_")
    val countryInfo: CountryInfo,*/

)