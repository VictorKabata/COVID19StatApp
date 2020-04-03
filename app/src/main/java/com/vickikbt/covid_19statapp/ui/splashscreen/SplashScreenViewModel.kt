package com.vickikbt.covid_19statapp.ui.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.util.Facts

class SplashScreenViewModel : ViewModel() {
    private val _facts = MutableLiveData<String>()
    val factsRepo= Facts()

    val facts: MutableLiveData<String>
        get() = _facts

    init {
        _facts.value=factsRepo.getRandomFact()
    }

    fun getFacts(){
        _facts.value=factsRepo.getRandomFact()
    }
}