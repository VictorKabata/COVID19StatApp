package com.vickikbt.covid_19statapp.ui.global

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.vickikbt.covid_19statapp.network.ConnectivityInterceptorImpl
import com.vickikbt.covid_19statapp.network.CoronaAPIService
import com.vickikbt.covid_19statapp.network.GlobalStatNetworkDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlobalViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val _cases = MutableLiveData<String>()
    val apiService = CoronaAPIService(ConnectivityInterceptorImpl(this.context!!))
    val coronaDataSource = GlobalStatNetworkDataSourceImpl(apiService)

    val cases: MutableLiveData<String>
        get() = _cases

    init {
        getGlobalCoronaInfectionStats()
    }

    private fun getGlobalCoronaInfectionStats() {
        coronaDataSource.downloadedGlobalStats.observeForever(Observer {
            _cases.value = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            coronaDataSource.fetchCurrentGlobalStat()
        }
    }
}
