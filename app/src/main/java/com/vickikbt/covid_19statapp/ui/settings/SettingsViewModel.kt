package com.vickikbt.covid_19statapp.ui.settings

import android.view.View
import androidx.lifecycle.ViewModel
import com.vickikbt.covid_19statapp.util.view.sendFeedback
import com.vickikbt.covid_19statapp.util.view.showAboutDeveloperDialog
import com.vickikbt.covid_19statapp.util.view.toast

internal class SettingsViewModel : ViewModel() {

    fun darkMode(view: View) {
        //TODO: Remove this and add them to the viewModel

        view.context.toast("Implementation is underway")
        /*val appSettingPref: SharedPreferences =
            this.activity!!.getSharedPreferences("ApSettingsPref", 0)

        val sharedPrefSave: SharedPreferences.Editor = appSettingPref.edit()
        val isNightModeOn: Boolean = appSettingPref.getBoolean("NightMode", false)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding.relativeLayoutTheme.setOnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefSave.putBoolean("NightMode", false)
                sharedPrefSave.apply()
            } else {
                sharedPrefSave.putBoolean("NightMode", true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefSave.apply()
            }
        }*/
    }

    fun sendFeedback(view: View) {
        view.context.sendFeedback(
            "victorbro14@gmail.com",
            "Covid-19 Stat App Feedback"
        )
    }

    fun aboutDev(view: View) {
        view.context.showAboutDeveloperDialog()
    }


}