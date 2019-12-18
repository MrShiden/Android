package com.shiden.preferenceactivity

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import java.security.Key


/*class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val countingPreference = findPreference<EditTextPreference>("signature") as EditTextPreference

            countingPreference.summaryProvider =
                Preference.SummaryProvider<EditTextPreference> { preference ->
                    val text = preference.text
                    if (TextUtils.isEmpty(text)) {
                        "Sin valores"
                    } else {
                       text
                    }
                }

        }
    }
}*/