package com.vickikbt.covid_19statapp.ui.global

import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.db.repository.CoronaRepository
import com.vickikbt.covid_19statapp.util.lazyDefered

class GlobalViewModel(private val coronaRepository: CoronaRepository) : ViewModel() {

    val stats by lazyDefered {
        coronaRepository.getGlobalStat()
    }
}
