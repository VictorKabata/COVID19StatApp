package com.vickikbt.covid_19statapp.ui.countries

import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.data.repository.CoronaRepository
import com.vickikbt.covid_19statapp.util.coroutines.lazyDefered

internal class CountriesFragmentViewModel(
    private val coronaRepository: CoronaRepository
) : ViewModel() {

    /*private val dataList: MutableList<CountriesCoronaDataEntry> = mutableListOf()
    private lateinit var myAdapter: RecyclerViewAdapter

    val search = ObservableField<String?>()

    val searchWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}


        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            myAdapter.countriesFilteredList
        }
    }*/
    val countriesStatistics by lazyDefered {
        coronaRepository.getCountriesStat()
    }
}