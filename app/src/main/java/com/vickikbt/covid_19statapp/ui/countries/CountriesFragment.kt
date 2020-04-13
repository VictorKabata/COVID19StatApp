package com.vickikbt.covid_19statapp.ui.countries

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.data.adapter.RecyclerViewAdapter
import com.vickikbt.covid_19statapp.databinding.FragmentCountriesBinding
import com.vickikbt.covid_19statapp.data.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.util.coroutines.ScopedFragment
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

internal class CountriesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val factory: CountriesFragmentViewModelFactory by instance()

    private val dataList: MutableList<CountriesCoronaDataEntry> = mutableListOf()


    private lateinit var viewModel: CountriesFragmentViewModel
    private lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(CountriesFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)
        binding.viewmodel = viewModel

        setHasOptionsMenu(true)

        initRecyclerView()

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }

            private fun filterList(filterItem: String) {
                val tempList: MutableList<CountriesCoronaDataEntry> = ArrayList()

                for (i in dataList) {
                    if (filterItem in i.country) {
                        tempList.add(i)
                    }
                }
                recyclerView_countries.apply {
                    adapter = RecyclerViewAdapter(tempList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Does nothing at the moment.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Does nothing at the moment.
            }

        })

        return binding.root
    }

    private fun initRecyclerView() = launch(Dispatchers.Main) {
        recyclerView_countries.apply {

            val currentCountriesStat = viewModel.countriesStatistics.await()

            currentCountriesStat.observe(viewLifecycleOwner, Observer {
                binding.progressBarGlobal.visibility = View.GONE

                dataList.addAll(it)
                adapter?.notifyDataSetChanged()
            })

            adapter = RecyclerViewAdapter(dataList)
        }
    }
}