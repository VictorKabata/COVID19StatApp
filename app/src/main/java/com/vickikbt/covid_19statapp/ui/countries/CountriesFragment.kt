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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

internal class CountriesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CountriesFragmentViewModelFactory by instance()

    private val dataList: MutableList<CountriesCoronaDataEntry> = mutableListOf()
    lateinit var myAdapter: RecyclerViewAdapter

    private lateinit var viewModel: CountriesFragmentViewModel
    private lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CountriesFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)
        setHasOptionsMenu(true)

        initRecyclerView()

         binding.editTextSearch.addTextChangedListener(object: TextWatcher {
             override fun afterTextChanged(s: Editable?) {
                 filterList(s.toString())
             }

             private fun filterList(filterItem: String) {
                 val tempList:MutableList<CountriesCoronaDataEntry> =ArrayList()

                 for (i in dataList){
                     if (filterItem in i.country){
                         tempList.add(i)
                     }
                 }

                 myAdapter=
                     RecyclerViewAdapter(
                         tempList
                     )
                 binding.recyclerViewCountries.adapter=myAdapter
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
        myAdapter =
            RecyclerViewAdapter(
                dataList
            )
        val currentCountriesStat = viewModel.countriesStatistics.await()

        currentCountriesStat.observe(viewLifecycleOwner, Observer {
            binding.progressBarGlobal.visibility = View.GONE

            dataList.addAll(it)
            myAdapter.notifyDataSetChanged()
        })

        binding.recyclerViewCountries.adapter = myAdapter
    }


    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.countries_toolbar_menu, menu)
        val menuItem = menu.findItem(R.id.action_search)

        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    filterList(query.toString())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText.toString())
                    return true
                }

                private fun filterList(filterItem: String) {
                    val tempList: MutableList<CountriesCoronaDataEntry> = ArrayList()

                    for (i in dataList) {
                        if (filterItem in i.country) {
                            tempList.add(i)
                            myAdapter.notifyDataSetChanged()
                        }
                    }

                    //myAdapter = RecyclerViewAdapter(tempList)
                    binding.recyclerViewCountries.adapter = myAdapter
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }*/

}


