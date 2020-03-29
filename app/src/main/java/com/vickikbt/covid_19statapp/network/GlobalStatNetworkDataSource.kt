package com.vickikbt.covid_19statapp.network

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData

interface GlobalStatNetworkDataSource {
    val downloadedGlobalStats:LiveData<GlobalCoronaData>

    suspend fun fetchCurrentGlobalStat()
}