package com.vickikbt.covid_19statapp.data


import com.google.gson.annotations.SerializedName

data class CountriesCoronaDataItem(
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Int,
    val country: String,
    val countryInfo: CountryInfo,
    val critical: Int,
    val deaths: Int,
    val deathsPerOneMillion: Int,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int,
    val updated: Long
)