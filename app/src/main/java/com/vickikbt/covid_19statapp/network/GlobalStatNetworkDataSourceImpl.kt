package com.vickikbt.covid_19statapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData
import com.vickikbt.covid_19statapp.util.NoConnectivityException

class GlobalStatNetworkDataSourceImpl(private val coronaAPIService: CoronaAPIService) :
    GlobalStatNetworkDataSource {
    private val _downloadedGlobalStat = MutableLiveData<GlobalCoronaData>()

    override val downloadedGlobalStats: LiveData<GlobalCoronaData>
        get() = _downloadedGlobalStat

    override suspend fun fetchCurrentGlobalStat() {
        try {
            val fetchCurrentGlobal = coronaAPIService
                .getGlobalStatistics()
                .await()
            _downloadedGlobalStat.postValue(fetchCurrentGlobal)
        }
        catch (e:NoConnectivityException){
            Log.e("Debugg", "No internet connection!", e)
        }
    }
}