package com.vickikbt.covid_19statapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vickikbt.covid_19statapp.databinding.FragmentCovidBinding


class CovidFragment : Fragment() {

    lateinit var binding: FragmentCovidBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_covid, container, false)

        binding.buttonLearnMore.setOnClickListener {
            openYouTubeVid()
        }

        return binding.root
    }

    private fun openYouTubeVid() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://youtu.be/BtN-goy9VOY")

        val chooser = Intent.createChooser(intent, "Open YouTube video using: ")
        startActivity(chooser)

    }

}
