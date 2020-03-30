package com.vickikbt.covid_19statapp.data


import com.google.gson.annotations.SerializedName

data class CountryInfo(
    val flag: String,
    @SerializedName("_id")
    val id: Int,
    val lat: Int,
    val long: Int
)