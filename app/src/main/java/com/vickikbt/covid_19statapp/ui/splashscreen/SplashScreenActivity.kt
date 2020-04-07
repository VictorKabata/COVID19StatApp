package com.vickikbt.covid_19statapp.ui.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.databinding.ActivitySplashScreenBinding
import com.vickikbt.covid_19statapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import spencerstudios.com.bungeelib.Bungee

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding
    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        val appSettingPref: SharedPreferences = getSharedPreferences("ApSettingsPref", 0)
        val isNightModeOn: Boolean = appSettingPref.getBoolean("NightMode", false)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        viewModel.facts.observe(this, Observer {
            textView_fact.text = it
        })

        Handler().postDelayed(Runnable {
            val splash = Intent(this, MainActivity::class.java)
            startActivity(splash)
            Bungee.slideLeft(this)
            finish()
        }, 3000)

    }
}
