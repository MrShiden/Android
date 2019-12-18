package com.shiden.demo

import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.*
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragment
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

            val player1 = findPreference<EditTextPreference>("nombre") as EditTextPreference
            //val player2 = findPreference<EditTextPreference>("prePlayer2") as EditTextPreference


            player1.summaryProvider =
                Preference.SummaryProvider<EditTextPreference> { preference ->
                    val text = preference.text
                    if (TextUtils.isEmpty(text)) {
                        "Player 1"
                    } else {
                        text
                    }
                }
           /* player2.summaryProvider =
                Preference.SummaryProvider<EditTextPreference> { preference ->
                    val text = preference.text
                    if (TextUtils.isEmpty(text)) {
                        "Player 2"
                    } else {
                        text
                    }
                }*/


        }
    }
}*/


/*class SettingsActivity: PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.root_preferences)




    }


}*/

