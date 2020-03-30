package com.vickikbt.covid_19statapp.ui.global

import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.db.repository.CoronaRepository

class GlobalViewModel(private val coronaRepository: CoronaRepository) : ViewModel() {
    val globalStatistics by lazy {
        coronaRepository.getGlobalStat()
    }

}
