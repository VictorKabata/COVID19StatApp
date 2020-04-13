package com.vickikbt.covid_19statapp.data.network

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.data.model.CountriesCoronaDataResponse
import com.vickikbt.covid_19statapp.data.db.entity.GlobalCoronaData

interface CoronaStatNetworkDataSource {
    val downloadedGlobalStats: LiveData<GlobalCoronaData>

    val downloadCountriesStat: LiveData<CountriesCoronaDataResponse>

    suspend fun fetchGlobalStat()

    suspend fun fetchCountriesStat()
}