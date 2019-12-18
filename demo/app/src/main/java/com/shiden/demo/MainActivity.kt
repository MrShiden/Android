package com.shiden.demo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.settings_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSettings = findViewById<Button>(R.id.button)



        btnSettings.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingActivity ::class.java))


            var btnNombre = findViewById<Button>(R.id.ver)
            var texto = findViewById<TextView>(R.id.textView)


            btnNombre.setOnClickListener(View.OnClickListener {

                var shaerd:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)

                texto.text = shaerd.getString("nombre", "")

            })








        }


    }
}
