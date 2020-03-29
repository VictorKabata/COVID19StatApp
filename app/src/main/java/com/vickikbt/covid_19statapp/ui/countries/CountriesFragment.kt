package com.vickikbt.covid_19statapp.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vickikbt.covid_19statapp.R

class CountriesFragment : Fragment() {

    private lateinit var countriesViewModel: CountriesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        countriesViewModel =
                ViewModelProviders.of(this).get(CountriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_countries, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        countriesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
