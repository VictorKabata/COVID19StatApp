package com.vickikbt.covid_19statapp.util

import java.util.*

class Facts {
    private val facts = arrayOf(
        "Always remember to wash your hands.",
        "Keep a distance of 1 meter between you and someone who's showing symptoms.",
//        "The government is making us quarantine so they can change the batteries in the pigeons.",
        "Practice social distancing...United we fall, divided we stand.",
        "Stay home, Stay safe.",
        "When in offline mode, you can only view statistics from when you last had an internet connection.",
        "Before sharing any information related to COVID-19, first ensure that they are true to avoid spreading hysteria. "


//        "Remember saying 2020 is your year?"

    )

    fun getRandomFact(): String {
        val random = Random()
        val randomNumber = random.nextInt(facts.size)
        return facts[randomNumber]
    }
}
