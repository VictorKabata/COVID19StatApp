package com.vickikbt.covid_19statapp.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.network.CoronaAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlobalViewModel : ViewModel() {
    private val _cases = MutableLiveData<String>()
    val apiService=CoronaAPIService()

    val cases: MutableLiveData<String>
        get() = _cases

    init {
        getGlobalCoronaInfectionStats()
    }

    private fun getGlobalCoronaInfectionStats(){
        GlobalScope.launch(Dispatchers.Main){
            val coronaGlobalCoronaData=apiService.getGlobalStatistics().await()
            _cases.value=coronaGlobalCoronaData.toString()
        }
    }
}