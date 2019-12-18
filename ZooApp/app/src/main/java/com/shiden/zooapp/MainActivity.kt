package com.shiden.zooapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfAnimals.add(Animal("Babuino" , "Estos desgraciados tienen el culo rojo", R.drawable.baboon))
        listOfAnimals.add(Animal("Bulldog" , "Estos desgraciados son chatos", R.drawable.bulldog))
        listOfAnimals.add(Animal("Panda" , "Estos desgraciados son blancos con negro", R.drawable.panda))
        listOfAnimals.add(Animal("Swallow Bird" , "Estos desgraciados vuelan", R.drawable.swallow_bird))
        listOfAnimals.add(Animal("tigre blanco" , "Estos desgraciados son blancos", R.drawable.white_tiger))
        listOfAnimals.add(Animal("Zebra" , "Estos desgraciados son unos desgraciados", R.drawable.zebra))

        adapter = AnimalsAdapter(this,listOfAnimals)
        tvListAnimal.adapter = adapter
    }

    class AnimalsAdapter:BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context:Context?= null
        constructor(context: Context, listOfAnimals:ArrayList<Animal>):super(){

            this.listOfAnimals = listOfAnimals
            this.context = context

        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val animal = listOfAnimals [p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myview = inflator.inflate(R.layout.animal_ticket,null)
            myview.tvName.text = animal.name!!
            myview.tvDes.text = animal.des!!
            myview.ivAnimals.setImageResource(animal.image!!)
            return myview


        }

        override fun getItem(p0: Int): Any {


            return listOfAnimals  [p0]

        }

        override fun getItemId(p0: Int): Long {

        return p0.toLong()

        }

        override fun getCount(): Int {

            return listOfAnimals.size

        }
    }


}
