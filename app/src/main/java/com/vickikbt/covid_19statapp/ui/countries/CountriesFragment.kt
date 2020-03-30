package com.vickikbt.covid_19statapp.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.databinding.FragmentCountriesBinding
import com.vickikbt.covid_19statapp.util.ScopedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CountriesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory:CountriesFragmentViewModelFactory by instance()

    private lateinit var viewModel: CountriesFragmentViewModel
    lateinit var binding:FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this,viewModelFactory).get(CountriesFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_countries, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUI()
    }

    private fun bindUI()=launch(Dispatchers.Main) {
        val countriesStats=viewModel.countriesStatistics.await()
        countriesStats.observe(viewLifecycleOwner, Observer {
            if (it!=null)return@Observer
        })
    }

}
