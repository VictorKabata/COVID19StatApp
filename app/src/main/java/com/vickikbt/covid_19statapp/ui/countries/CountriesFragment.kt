package com.vickikbt.covid_19statapp.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R

class CountriesFragment : Fragment() {

    private lateinit var fragmentViewModel: CountriesFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewModel = ViewModelProvider(this).get(CountriesFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_countries, container, false)

        return root
    }
}
