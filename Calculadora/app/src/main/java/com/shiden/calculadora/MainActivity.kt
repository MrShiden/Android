package com.shiden.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buEq.isEnabled=false
        operadores(false)



    }


        var equalbtn = true








    fun buNumberEvent(view:View){



        if (newOp == true){

            etShowNumber.setText("")


        }

        operadores(true)



        newOp = false

        val buttonSelect = view as Button
        var buClickValue:String= etShowNumber.text.toString()
        when(buttonSelect.id){

            bu0.id->{

                buClickValue += "0"


            }


            bu1.id->{

                buClickValue += "1"


            }

            bu2.id->{

                buClickValue += "2"


            }

            bu3.id->{

                buClickValue += "3"


            }

            bu4.id->{

                buClickValue += "4"


            }

            bu5.id->{

                buClickValue += "5"


            }

            bu6.id->{

                buClickValue += "6"


            }

            bu7.id->{

                buClickValue += "7"


            }

            bu8.id->{

                buClickValue += "8"


            }

            bu9.id->{

                buClickValue += "9"


            }

            buDot.id->{

                //TODO: prevenir que se puedan poner mas de un boton

                buClickValue += "."


            }

            buPlusMinus.id->{



                buClickValue = "-" +  buClickValue



            }





        }



        etShowNumber.setText(buClickValue)




    }


    var oldNumber = ""
    var newOp = true
    var op = "*"



    fun buOpEvent(view: View){

        val buSelect = view as Button

        when(buSelect.id) {

            buDev.id -> {


                op = "/"


            }
            buMul.id -> {


                op = "*"


            }

            buSub.id->{


                op = "-"


            }

            buSum.id->{


                op = "+"


            }

        }

        oldNumber = etShowNumber.text.toString()

        newOp = true

        equalbtn = false

        if (equalbtn == false){

            buEq.isEnabled = true

        }




    }



    fun buEqEvent (view: View) {







        val newNumber = etShowNumber.text.toString()
        var finalNumber:Double? = null
        when(op){

            "/"-> {

                finalNumber = oldNumber.toDouble() /newNumber.toDouble()


            }

            "*" ->{

                finalNumber = oldNumber.toDouble() *newNumber.toDouble()

            }

            "-"-> {

                finalNumber = oldNumber.toDouble() -newNumber.toDouble()


            }

            "+" ->{

                finalNumber = oldNumber.toDouble() +newNumber.toDouble()



            }



        }

        etShowNumber.setText(finalNumber.toString())
        newOp = true



            buEq.isEnabled = false





    }

    fun porcentaje (view: View){

        var numero:Double = etShowNumber.text.toString().toDouble()/100

        etShowNumber.setText(numero.toString())
        newOp = true

    }

    fun clean (view: View){

        etShowNumber.setText("")

        newOp = true
        operadores(false)
        buEq.isEnabled = false


    }


    fun operadores (reto:Boolean){

        buSum.isEnabled = reto
        buSub.isEnabled = reto
        buMul.isEnabled = reto
        buDev.isEnabled = reto
        buPo.isEnabled = reto


    }

}


