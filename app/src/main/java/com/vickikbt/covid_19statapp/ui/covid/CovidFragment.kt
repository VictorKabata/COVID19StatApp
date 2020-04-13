package com.vickikbt.covid_19statapp.ui.covid

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.util.view.openYoutube
import kotlinx.android.synthetic.main.fragment_covid.*


internal class CovidFragment : Fragment(R.layout.fragment_covid) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_learn_more.setOnClickListener {
            context?.openYoutube()
        }
    }
}
