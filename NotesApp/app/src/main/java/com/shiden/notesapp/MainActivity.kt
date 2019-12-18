package com.shiden.notesapp


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {


    var listNotes = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listNotes.add(Note(1,"Que pedo", "1"))
        //listNotes.add(Note(2,"Que tranza", "2"))
        //listNotes.add(Note(3,"Que onda", "3"))



        loadQuery("%")

    }

    override fun onResume() {
        super.onResume()

        loadQuery("%")
    }

    fun loadQuery(title: String){

        var dbManager =DbManager(this)
        val projections = arrayOf("ID","title", "Description")
        val selectionArgs =  arrayOf(title)
        val cursor=dbManager.Query(projections, "title like ?", selectionArgs,"title")
        listNotes.clear()
        if (cursor.moveToFirst()){

            do{

                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val title=cursor.getString(cursor.getColumnIndex("title"))
                val description=cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note(ID,title,description))

            }while (cursor.moveToNext())

        }

        var myNotesAdapter = MyNotesAdapter(this,listNotes)
        lvNotes.adapter = myNotesAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_SHORT).show()
                loadQuery("%$query%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item != null) {

            when (item.itemId) {

                R.id.addNote -> {
                    //ir a add page
                    var intent = Intent(this, AddNotes::class.java)


                    startActivity(intent)

                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class MyNotesAdapter:BaseAdapter{
        var listNotesAdapter = ArrayList<Note>()
        var context:Context? = null
        constructor(context: Context, listNotesAdapter:ArrayList<Note>):super(){
            this.listNotesAdapter=listNotesAdapter
            this.context=context


        }

        override fun getView(p0: Int, p1: View?, p2:ViewGroup?): View {

            var myView= layoutInflater.inflate(R.layout.ticket,null)
            var myNode = listNotesAdapter[p0]
            myView.tvTitle.text = myNode.nodeName
            myView.tvContent.text = myNode.nodeDes
            myView.btnBorrar.setOnClickListener(View.OnClickListener {

                var dbManager=DbManager(this.context!!)
                val selectionArgs =  arrayOf(myNode.nodeID.toString())
                dbManager.Delete("ID=?",selectionArgs)

            })

            myView.btnEditar.setOnClickListener(View.OnClickListener {

                GoToUpdate(myNode)

            })

            return myView

        }

        override fun getItem(p0: Int): Any {

            return listNotesAdapter[p0]

        }

        override fun getItemId(p0: Int): Long {

            return p0.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }

    }

    fun GoToUpdate(note: Note){

        var intent = Intent(this, AddNotes::class.java)
        intent.putExtra("ID",note.nodeID)
        intent.putExtra("name",note.nodeID)
        intent.putExtra("des",note.nodeID)

        startActivity(intent)
    }
}