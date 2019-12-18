package com.shiden.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Nom:EditText = findViewById(R.id.nom)
        var Edat:EditText =  findViewById(R.id.edat)
        var Alcada:EditText = findViewById(R.id.alcada)
        val btllegeix:Button = findViewById(R.id.btnLeer)
        val btguarda:Button = findViewById(R.id.btnGuardar)

        btllegeix.setOnClickListener(View.OnClickListener {
            var llegeixprefe: SharedPreferences = getSharedPreferences("dades", Context.MODE_PRIVATE)
            Nom.setText(llegeixprefe.getString("nomguarda",""))
            Edat.setText(llegeixprefe.getInt("edatguarda",0).toString())
            Alcada.setText(llegeixprefe.getFloat("alcadaguarda", 0f).toString())

        })
        btguarda.setOnClickListener(View.OnClickListener {
            var escriureprefe: SharedPreferences = getSharedPreferences("dades", Context.MODE_PRIVATE)
            var editor:SharedPreferences.Editor = escriureprefe.edit()
            editor.putString("nomguarda", Nom.text.toString())
            editor.putInt("edatguarda", Edat.text.toString().toInt())
            editor.putFloat("alcadaguarda", Alcada.text.toString().toFloat())
            editor.apply()

        })
    }
}
