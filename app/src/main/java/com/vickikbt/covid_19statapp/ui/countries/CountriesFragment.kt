package com.vickikbt.covid_19statapp.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.adapter.RecyclerViewAdapter
import com.vickikbt.covid_19statapp.databinding.FragmentCountriesBinding
import com.vickikbt.covid_19statapp.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.network.ConnectivityInterceptorImpl
import com.vickikbt.covid_19statapp.network.CoronaAPIService
import com.vickikbt.covid_19statapp.network.CoronaStatNetworkDataSourceImpl
import com.vickikbt.covid_19statapp.util.ScopedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CountriesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CountriesFragmentViewModelFactory by instance()

    private val dataList: MutableList<CountriesCoronaDataEntry>? = mutableListOf()
    lateinit var myAdapter: RecyclerViewAdapter

    private lateinit var viewModel: CountriesFragmentViewModel
    lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CountriesFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)

        initRecyclerView2()


        return binding.root
    }

    private fun initRecyclerView() {
        val apiService = CoronaAPIService(ConnectivityInterceptorImpl(this.context!!))
        val networkDataSource = CoronaStatNetworkDataSourceImpl(apiService)

        myAdapter = RecyclerViewAdapter(dataList!!)

        GlobalScope.launch(Dispatchers.Main) {
            networkDataSource.fetchCountriesStat()
            binding.progressBarGlobal.visibility = View.GONE
        }

        networkDataSource.downloadCountriesStat.observe(viewLifecycleOwner, Observer {
            dataList.addAll(it)
            myAdapter.notifyDataSetChanged()

        })

    }

    private fun initRecyclerView2() = launch(Dispatchers.Main) {

        myAdapter = RecyclerViewAdapter(dataList!!)
        val currentCountriesStat = viewModel.countriesStatistics.await()

        currentCountriesStat.observe(viewLifecycleOwner, Observer {

            binding.progressBarGlobal.visibility = View.GONE

            dataList.addAll(it)
            myAdapter.notifyDataSetChanged()

        })

        binding.recyclerViewCountries.adapter = myAdapter
    }


}



