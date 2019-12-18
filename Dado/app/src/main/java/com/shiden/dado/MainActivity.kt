package com.shiden.dado

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dado.*
import kotlinx.android.synthetic.main.dado.view.*
import java.util.zip.Inflater
import kotlin.random.Random
import com.shiden.dado.MainActivity as MainActivity1
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {

    var dadoImg: ImageView? = null
    var dadoImg2: ImageView? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDados.setOnClickListener {



            val dadosAlertDialog = LayoutInflater.from(this).inflate(R.layout.dado,null)
            val dadosBuilder = AlertDialog.Builder(this)
                .setView(dadosAlertDialog)

            val dAlertDialog = dadosBuilder.show()

            dadosAlertDialog.btnOk.setOnClickListener{

                dAlertDialog.dismiss()

            }

            dadosAlertDialog.btnRoll.setOnClickListener{




                rolldice()





            }

            dadoImg = findViewById(R.id.dado)
            dadoImg2 = findViewById(R.id.dado2)


        }

        btnSettings.setOnClickListener{



            startActivity(Intent(this, Settings ::class.java))

        }

            roller.setOnClickListener {

               val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)

                val fondo = sharedPreferences.getString("fondo","")

                roller.setBackgroundColor(Color.parseColor("$fondo"))

                rolldice()





            }

            dadoImg = findViewById(R.id.dado)
            dadoImg2 = findViewById(R.id.dado2)

        }


        private fun rolldice() {

            //TODO hacer un bucle para el cambio de imagen

            val numRandom = Random.nextInt(6) + 1
            val dadoLado = when (numRandom) {

                1 -> R.drawable.dado1_aq
                2 -> R.drawable.dado2_aq
                3 -> R.drawable.dado3_aq
                4 -> R.drawable.dado4_aq
                5 -> R.drawable.dado5_aq


                else -> R.drawable.dado6_aq
            }

            dadoImg?.setImageResource(dadoLado)

            if(numRandom == 6){

                Toast.makeText(this,"A caido 6 !",Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this,"A caido $numRandom !",Toast.LENGTH_SHORT).show()
            }


        }


    }


