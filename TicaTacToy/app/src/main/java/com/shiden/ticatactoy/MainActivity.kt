package com.shiden.ticatactoy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log
import java.util.*
import kotlin.math.log2


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun buClick (view: View){

        val btnSel = view as Button

        var btnId = 0

        when (btnSel.id){

            R.id.button -> btnId = 1
            R.id.button2 -> btnId = 2
            R.id.button3 -> btnId = 3
            R.id.button4 -> btnId = 4
            R.id.button5 -> btnId = 5
            R.id.button6 -> btnId = 6
            R.id.button7 -> btnId = 7
            R.id.button8 -> btnId = 8
            R.id.button9 -> btnId = 9


        }

        //Log.d("Boton apretado ", btnSel.id.toString())
        //Log.d("Boton apretado btn 1d ", btnId.toString())

        jugar(btnId,btnSel)
       // Log.d("Ficha1 ",jugador1.toString())
       // Log.d("Ficha2", jugador2.toString())




    }

    var jugadorActivo = 1
    var jugador1 =  arrayListOf<Int>()
    var jugador2 = arrayListOf<Int>()



    fun jugar(btnId:Int, btnSel:Button){

        if (jugadorActivo == 1){

            btnSel.text = "o"
            btnSel.setBackgroundResource(R.color.Negro)
            btnSel.setTextColor(Color.WHITE)
            jugador1.add(btnId)
            jugadorActivo = 2
            autoPlay()


        }else {


            btnSel.text = "x"
            btnSel.setBackgroundResource(R.color.blancoBoton)
            btnSel.setTextColor(Color.BLACK)
            jugador2.add(btnId)
            jugadorActivo = 1






        }



        btnSel.isEnabled = false

        checarGanador()



    }


    fun checarGanador(){

        var ganador = -1

        //fila 1

        if (jugador1.contains(1) && jugador1.contains(2) && jugador1.contains(3)){

            ganador = 1



        }
        if (jugador2.contains(1) && jugador2.contains(2) && jugador2.contains(3)) {

            ganador = 2

        }

        //fila2

        if (jugador1.contains(4) && jugador1.contains(5) && jugador1.contains(6)){

            ganador = 1


        }

        if (jugador2.contains(4) && jugador2.contains(5) && jugador2.contains(6)) {

            ganador = 2

        }

        //fila3
        if (jugador1.contains(7) && jugador1.contains(8) && jugador1.contains(9)){

            ganador = 1


        }

        if (jugador2.contains(7) && jugador2.contains(8) && jugador2.contains(9)){

            ganador = 2


        }
        // col 1
        if (jugador1.contains(1) && jugador1.contains(4) && jugador1.contains(7)) {
            ganador = 1
        }
        if (jugador2.contains(1) && jugador2.contains(4) && jugador2.contains(7)) {
            ganador = 2
        }


        // col 2
        if (jugador1.contains(2) && jugador1.contains(5) && jugador1.contains(8)) {
            ganador = 1
        }
        if (jugador2.contains(2) && jugador2.contains(5) && jugador2.contains(8)) {
            ganador = 2
        }


        // col 3
        if (jugador1.contains(3) && jugador1.contains(6) && jugador1.contains(9)) {
            ganador = 1
        }
        if (jugador2.contains(3) && jugador2.contains(6) && jugador2.contains(9)) {
            ganador = 2
        }

        if (ganador == 1){

            var lo1 = 0

            Toast.makeText(this,"El ganador de la partida es el jugador 1", Toast.LENGTH_SHORT).show()
           // contador(1)

            lo1 += 1

            Log.d("Log1",lo1.toString())

        }else if (ganador == 2){

            Toast.makeText(this,"El ganador de la partida es el jugador 2", Toast.LENGTH_SHORT).show()
            //contador(2)

            var lo2 = 0

            lo2 += 1

            Log.d("Log2", lo2.toString())

        }








    }

    fun autoPlay(){

        var celdasVacias = arrayListOf<Int>()


        for (btnId in 1..9){

            if (!(jugador1.contains(btnId) || jugador2.contains(btnId) )){

                celdasVacias.add(btnId)

            }


        }

        //Asegurarse de no usar la libreria de Kotlina para usar Random se tiene que usar esta libreria "import java.util.*" si no va a marcar error
        val r = Random()
        val ranIndex =  r.nextInt(celdasVacias.size)
        val btnid = celdasVacias[ranIndex]
        var btnSel:Button?

        btnSel = when (btnid){

            1 -> button
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9

            else -> {button}

        }

        jugar(btnid, btnSel)





    }


    fun resetGame(){



        jugador1.clear()
        jugador2.clear()







    }


    fun contador(contador:Int){

        var contadorJugador1 = 0
        var contadorJugador2 = 0



        if (contador == 1){

           contadorJugador1 += 1




        }
        if (contador == 2){

            contadorJugador2 += 1


        }

        Toast.makeText(this,"Jugador 1 $contadorJugador1 , Jugador 2 $contadorJugador2", Toast.LENGTH_LONG).show()








    }




}




