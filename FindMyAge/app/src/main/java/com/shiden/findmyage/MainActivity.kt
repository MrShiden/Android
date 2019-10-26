package com.shiden.findmyage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // en esta parte se esta llamando al boton por medio de setOnClickListener el cual esta a la espera de que sea apretado para poder llevar a cabo la tarea que se le asigne
        btEdad.setOnClickListener{



            val anioUsuario = etAno.text.toString()
            val anoActual = Calendar.getInstance().get(Calendar.YEAR)

           if (anioUsuario < 2019.toString()){

               calcularEdad()
           } else{


               tvEdad.text = "No creo que seas del futuro"

           }






        }





        }
    fun calcularEdad(){

        val edad = Integer.parseInt(etAno.text.toString())
        val anoActual = Calendar.getInstance().get(Calendar.YEAR)
        val anoUsuario = anoActual - edad
        tvEdad.text = "Tu edad es de $anoUsuario años"



    }
}






