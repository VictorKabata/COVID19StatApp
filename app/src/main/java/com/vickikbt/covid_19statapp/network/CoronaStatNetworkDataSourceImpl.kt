package com.vickikbt.covid_19statapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vickikbt.covid_19statapp.model.CountriesCoronaDataResponse
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData
import com.vickikbt.covid_19statapp.util.NoConnectivityException

class CoronaStatNetworkDataSourceImpl(private val coronaAPIService: CoronaAPIService) :
    CoronaStatNetworkDataSource {
    private val _downloadedGlobalStat = MutableLiveData<GlobalCoronaData>()

    override val downloadedGlobalStats: LiveData<GlobalCoronaData>
        get() = _downloadedGlobalStat

    override suspend fun fetchGlobalStat() {
        try {
            val fetchCurrentGlobal = coronaAPIService
                .getGlobalStatistics()
                .await()
            _downloadedGlobalStat.postValue(fetchCurrentGlobal)
        } catch (e: NoConnectivityException) {
            Log.e("Debug", "No internet connection!", e)
        }
    }




    private val _downloadedCountriesStat = MutableLiveData<CountriesCoronaDataResponse>()

    override val downloadCountriesStat: LiveData<CountriesCoronaDataResponse>
        get() = _downloadedCountriesStat

    override suspend fun fetchCountriesStat() {
        try {
            val fetchedCurrentCountries = coronaAPIService
                .getCountriesStatistics()
                .await()
            _downloadedCountriesStat.postValue(fetchedCurrentCountries)
        } catch (e: NoConnectivityException) {
            Log.e("Debug", "No internet connection!", e)
        }
    }

}