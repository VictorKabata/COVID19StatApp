package com.vickikbt.covid_19statapp.db.repository

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData

interface CoronaRepository {

    suspend fun getGlobalStat():LiveData<GlobalCoronaData>

    suspend fun getCountriesStat():LiveData<List<CountriesCoronaDataEntry>>
}