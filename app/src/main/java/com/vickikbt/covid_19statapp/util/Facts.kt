package com.vickikbt.covid_19statapp.util

import java.util.*

class Facts {
    private val facts = arrayOf(
        "Always remember to wash your hands.",
        "Keep a distance of 1 meter between you and someone who's showing symptoms.",
//        "The government is making us quarantine so they can change the batteries in the pigeons.",
        "March did not spread COVID-19 a lot but if we are not careful April-May.",
        "Practice social distancing...United we fall, divided we stand.",
        "Stay home, Stay safe.",
        "When in offline mode, you can only view statistics from when you last had an internet connection."


//        "Remember saying 2020 is your year?"

    )

    fun getRandomFact(): String {
        val random = Random()
        val randomNumber = random.nextInt(facts.size)
        return facts[randomNumber]
    }
}
