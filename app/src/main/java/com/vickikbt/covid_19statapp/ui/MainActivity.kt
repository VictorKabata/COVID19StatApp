package com.vickikbt.covid_19statapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


internal class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val appSettingPref: SharedPreferences = getSharedPreferences("ApSettingsPref", 0)
        val isNightModeOn: Boolean = appSettingPref.getBoolean("NightMode", false)
        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setupBottomNav()

        checkConnectivity()

    }



    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }

    private fun checkConnectivity() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            binding.relativeLayoutConnectivity.visibility = View.GONE
        } else {
            binding.relativeLayoutConnectivity.visibility = View.VISIBLE

            Handler().postDelayed(Runnable {
                binding.relativeLayoutConnectivity.visibility = View.GONE
            }, 3500)
        }

    }
}
