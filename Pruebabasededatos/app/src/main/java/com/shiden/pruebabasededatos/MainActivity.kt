package com.shiden.pruebabasededatos

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    var id = 0
    var player = "El jugador 1"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        iv_player1.setOnClickListener(){

            player = "El jugador 1"



            Toast.makeText(this,"Se a seleccionado el Jugador 1", Toast.LENGTH_SHORT).show()

        }

        iv_player2.setOnClickListener(){

            player = "El jugador 2"

            Toast.makeText(this,"Se a seleccionado el Jugador 2", Toast.LENGTH_SHORT).show()

        }


        btn_buscar.setOnClickListener(){

            var intent = Intent(this, Base::class.java)

            startActivity(intent)

        }





        try {
            var bundle:Bundle=intent.extras!!
            id=bundle.getInt("ID",0)
            if (id!=0) {
                et_accion.setText(bundle.getString("Accion"))
                et_descripcion.setText(bundle.getString("Descripcion"))



            }
        }catch (ex: Exception){




        }

    }

    fun buReset(view: View){

        var dbManager=DbManager(this)

        dbManager.DeleteData()

    }


    fun buSave(view: View) {

        var dbManager =DbManager(this)

        var values = ContentValues()
        values.put("Accion",et_accion.text.toString())
        values.put("Player", player)
        values.put("Descripcion", et_descripcion.text.toString())



        if (id==0) {
            val id = dbManager.Insert(values)
            if (id > 0) {
                Toast.makeText(this, "Mensaje almacenado", Toast.LENGTH_SHORT).show()

                et_accion.setText("")
                et_descripcion.setText("")


            } else {
                Toast.makeText(this, "No se puede almacenar el mensaje", Toast.LENGTH_SHORT).show()
            }

        }else{

            var selectionArs = arrayOf(id.toString())

            val id = dbManager.Update(values,"ID=?",selectionArs)

            if (id > 0) {
                Toast.makeText(this, "Mensaje almacenado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "No se puede almacenar el mensaje", Toast.LENGTH_SHORT).show()
            }

        }

    }




}
