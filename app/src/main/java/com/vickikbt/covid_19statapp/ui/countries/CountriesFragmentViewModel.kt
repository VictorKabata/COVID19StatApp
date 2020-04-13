package com.vickikbt.covid_19statapp.ui.countries

import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.data.repository.CoronaRepository
import com.vickikbt.covid_19statapp.util.lazyDefered

class CountriesFragmentViewModel(private val coronaRepository: CoronaRepository) : ViewModel() {
    val countriesStatistics by lazyDefered {
        coronaRepository.getCountriesStat()
    }
}