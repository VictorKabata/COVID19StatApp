package com.vickikbt.covid_19statapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.databinding.ActivityMainBinding
import com.vickikbt.covid_19statapp.network.ConnectivityInterceptor

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        checkConnectivity()

    }

    private fun checkConnectivity() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            binding.relativeLayoutConnectivity.visibility= View.GONE
        }else{
            binding.relativeLayoutConnectivity.visibility= View.VISIBLE
        }

    }
}
