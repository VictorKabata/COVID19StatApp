package com.vickikbt.covid_19statapp.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
            val recipient:String="victorbro14@gmail.com"
            val subject:String="Covid-19 Stat App Feedback"
            sendFeedback(recipient,subject)
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
         view.findViewById<TextView>(R.id.profile_emailAddress).setOnClickListener {
             val recipient:String="victorbro14@gmail.com"
             val subject:String="Covid-19 Stat App Feedback"
             sendFeedback(recipient,subject)
         }

         view.findViewById<TextView>(R.id.profile_website).setOnClickListener{
             val intent = Intent(Intent.ACTION_VIEW)
             intent.data = Uri.parse("https://victorkabata.github.io/")

             val chooser = Intent.createChooser(intent, "Open website using: ")
             //if (intent.resolveActivity(pac))
             startActivity(chooser)
         }

        view.findViewById<TextView>(R.id.profile_twitter).setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://victorkabata.github.io/") //TODO: Add my twitter handle here.

            val chooser = Intent.createChooser(intent, "Open Twitter using: ")
            //if (intent.resolveActivity(pac))
            startActivity(chooser)
        }


        val alertDialog = builder.create()
        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }

        alertDialog.show()
    }

    private fun sendFeedback(recipient:String,subject:String) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.data= Uri.parse("mailto:")
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL,recipient)
        intent.putExtra(Intent.EXTRA_SUBJECT,subject)

        try{
            val chooser = Intent.createChooser(intent, "Choose email client: ")
            startActivity(chooser)
        }catch (e:Exception){
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
