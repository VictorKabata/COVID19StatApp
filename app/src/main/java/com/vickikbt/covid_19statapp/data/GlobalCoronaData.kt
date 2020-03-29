package com.vickikbt.covid_19statapp.data

data class GlobalCoronaData(
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val updated: Long
)