package com.vickikbt.covid_19statapp.ui.settings

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel
    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        val appSettingPref: SharedPreferences =
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
        }

        binding.relativeLayoutFeedback.setOnClickListener {

        }

        binding.relativeLayoutDeveloper.setOnClickListener {
            showAboutDeveloperDialog()
        }

        return binding.root
    }

    private fun showAboutDeveloperDialog() {
        val builder = android.app.AlertDialog.Builder(activity, R.style.AlertDialogTheme)

        val view: View = LayoutInflater.from(activity).inflate(
            R.layout.about_developer_dialog, null
        )

        builder.setView(view)
        /* view.findViewById<TextView>(R.id.textviewDialogTitle).text = (resources.getString(R.string.exit_dialog_title))
         view.findViewById<TextView>(R.id.textviewDialogMessage).text = (resources.getString(R.string.exit_dialog_message))
         view.findViewById<Button>(R.id.buttonNo).text = (resources.getString(R.string.no))
         view.findViewById<Button>(R.id.buttonYes).text = (resources.getString(R.string.yes))*/

        val alertDialog = builder.create()

        /*view.findViewById<Button>(R.id.buttonNo).setOnClickListener {
            alertDialog.dismiss()
        }

        view.findViewById<Button>(R.id.buttonYes).setOnClickListener {
            moveTaskToBack(true)
            Process.killProcess(Process.myPid())
            exitProcess(1)
        }*/

        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }

        alertDialog.show()
    }
}
