package com.vickikbt.covid_19statapp.util

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.vickikbt.covid_19statapp.R

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).apply {
        show()
    }
}

fun Context.openYoutube() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("https://youtu.be/BtN-goy9VOY")

    val chooser = Intent.createChooser(intent, "Open YouTube video using: ")
    startActivity(chooser)
}


fun Context.sendFeedback(recipient: String, subject: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.data = Uri.parse("mailto:")
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_EMAIL, recipient)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)

    try {
        val chooser = Intent.createChooser(intent, "Choose email client: ")
        startActivity(chooser)
    } catch (e: Exception) {
        toast(e.message.toString())
    }
}



fun Context.website() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("https://victorkabata.github.io/")
    val chooser = Intent.createChooser(intent, "Open website using: ")
    startActivity(chooser)
}


fun Context.twitter() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data =
        Uri.parse("https://victorkabata.github.io/") //TODO: Add my twitter handle here.

    val chooser = Intent.createChooser(intent, "Open Twitter using: ")
    startActivity(chooser)
}



fun Context.showAboutDeveloperDialog() {
    val builder = android.app.AlertDialog.Builder(this, R.style.AlertDialogTheme)

    val view: View = LayoutInflater.from(this).inflate(
        R.layout.about_developer_dialog, null
    )

    builder.setView(view)
    view.findViewById<TextView>(R.id.profile_emailAddress).setOnClickListener {

        sendFeedback(
            "victorbro14@gmail.com",
            "Covid-19 Stat App Feedback"
        )
    }

    view.findViewById<TextView>(R.id.profile_website).setOnClickListener {
        website()
    }

    view.findViewById<TextView>(R.id.profile_twitter).setOnClickListener {
        twitter()
    }

    val alertDialog = builder.create()
    if (alertDialog.window != null) {
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
    }

    alertDialog.show()
}