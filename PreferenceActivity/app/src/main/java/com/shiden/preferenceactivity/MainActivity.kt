package com.shiden.preferenceactivity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import androidx.preference.PreferenceRecyclerViewAccessibilityDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSettings = findViewById<Button>(R.id.btnSettings)


        var intent = Intent(this@MainActivity,Opciones :: class.java )


        btnSettings.setOnClickListener(View.OnClickListener {

           /* val vistaDialogo = LayoutInflater.from(this).inflate(R.layout.datos,null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(vistaDialogo)
                .setTitle("Forma Login")
            val mAlertDialog = mBuilder.show()*/


            startActivity(intent)
            })


        var btnValores = findViewById<Button>(R.id.btnValores)

        btnValores.setOnClickListener(View.OnClickListener {

        var leer:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)

            valores.text =leer.getString("key_full_name","")

            if (valores.text == ""){

                valores.text = "Tiene un valor de 8000"

            }



        })








    }
}
