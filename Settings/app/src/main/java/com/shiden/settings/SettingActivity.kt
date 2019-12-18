/*package com.shiden.settings

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.view.View
import java.util.prefs.PreferenceChangeEvent

class SettingsActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentManager.beginTransaction().replace(android.R.id.content, MainsettingsFragment())
            .commit()
    }


    class MainsettingsFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences)
        }
    }


}*/

package com.shiden.settings

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.preference.RingtonePreference
import android.text.TextUtils

import java.util.prefs.Preferences

class SettingActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentManager.beginTransaction().replace(android.R.id.content, MainSettingsFragments())
            .commit()
    }

    class MainSettingsFragments : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences)

            bindSummaryValue(findPreference("key_full_name"))
            bindSummaryValue(findPreference("key_email"))
            bindSummaryValue(findPreference("key_sleep_timer"))
            bindSummaryValue(findPreference("key_music_quality"))
            bindSummaryValue(findPreference("key_notification_ringtone"))
        }
    }

    companion object {

        private fun bindSummaryValue(preference: Preference) {

            preference.onPreferenceChangeListener = listener
            listener.onPreferenceChange(
                preference,
                PreferenceManager.getDefaultSharedPreferences(preference.context).getString(
                    preference.key,
                    ""
                )
            )

        }


        private val listener = Preference.OnPreferenceChangeListener { preference, newValue ->
            val stringValue = newValue.toString()
            if (preference is ListPreference) {

                val index = preference.findIndexOfValue(stringValue)

                //Poner los valores en el Summary que les corresponde
                preference.setSummary(if (index > 0) preference.entries[index] else null)

            } else if (preference is EditTextPreference) {
                preference.setSummary(stringValue)
            } else if (preference is RingtonePreference) {

                if (TextUtils.isEmpty(stringValue)) {
                    preference.setSummary("Silent")
                } else {
                    val ringtone =
                        RingtoneManager.getRingtone(preference.getContext(), Uri.parse(stringValue))

                    if (ringtone == null) {

                        preference.setSummary("Choose notification ringtone")


                    } else {
                        val name = ringtone.getTitle(preference.getContext())
                        preference.setSummary(name)
                    }

                }
            }


            true
        }
    }

}





