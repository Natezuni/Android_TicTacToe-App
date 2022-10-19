package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model: MyViewModel by viewModels()

        setContentView(R.layout.settings_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val switch = findViewById<Switch>(R.id.switch2)
        if (model.state == "Switch1:ON") {
            switch.isChecked = true
        }

        if (switch.isChecked) {
            model.state = "Switch1:ON"
        } else {
            model.state = "Switch1:OFF"
        }

//        switch?.setOnCheckedChangeListener { _, isChecked ->
//
//            if (isChecked) {
//                model.state = "Switch1:ON"
//
//            } else {
//                model.state = "Switch1:OFF"
//            }
//
//        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }



}