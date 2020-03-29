package com.vickikbt.covid_19statapp.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.databinding.FragmentGlobalBinding
import kotlinx.android.synthetic.main.fragment_global.*

class GlobalFragment : Fragment() {

    lateinit var binding: FragmentGlobalBinding
    private lateinit var viewModel: GlobalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GlobalViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_global, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.cases.observe(viewLifecycleOwner, Observer {
            text_home.text = it
        })

    }

}
