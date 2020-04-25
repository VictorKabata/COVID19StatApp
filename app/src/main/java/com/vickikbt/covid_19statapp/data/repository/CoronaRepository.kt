package com.vickikbt.covid_19statapp.data.repository

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.data.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.data.db.entity.GlobalCoronaData

interface CoronaRepository {

    suspend fun getGlobalStat(): LiveData<GlobalCoronaData>

    suspend fun getCountriesStat(): LiveData<List<CountriesCoronaDataEntry>>
}