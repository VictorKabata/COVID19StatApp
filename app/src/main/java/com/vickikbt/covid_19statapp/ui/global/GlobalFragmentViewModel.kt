package com.vickikbt.covid_19statapp.ui.global

import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.data.repository.CoronaRepository
import com.vickikbt.covid_19statapp.util.coroutines.lazyDefered
import java.text.DateFormat
import java.util.*

internal class GlobalFragmentViewModel(
    private val coronaRepository: CoronaRepository
) : ViewModel() {

    val globalStatistics by lazyDefered {
        coronaRepository.getGlobalStat()
    }

    fun getDate(): String {
        val calendar: Calendar = Calendar.getInstance()
        return DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
    }

}
