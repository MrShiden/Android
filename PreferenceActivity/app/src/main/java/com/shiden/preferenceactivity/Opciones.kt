package com.shiden.preferenceactivity

import android.os.Bundle
import android.preference.PreferenceActivity
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.Preference

class Opciones: PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.opciones)




    }


}

