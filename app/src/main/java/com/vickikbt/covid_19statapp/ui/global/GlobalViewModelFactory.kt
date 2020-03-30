package com.vickikbt.covid_19statapp.ui.global

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.db.repository.CoronaRepository

@Suppress("UNCHECKED_CAST")
class GlobalViewModelFactory(private val coronaRepository: CoronaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GlobalViewModel(coronaRepository) as T
    }
}