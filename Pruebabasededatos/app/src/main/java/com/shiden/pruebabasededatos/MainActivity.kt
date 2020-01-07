package com.shiden.pruebabasededatos

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaScannerConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.*
import java.io.File
import java.lang.Exception
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_seleccionar__imagen.*
import kotlinx.android.synthetic.main.activity_seleccionar__imagen.view.*
import kotlinx.android.synthetic.main.activity_seleccionar_imagen.*
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {


    var id = 0
    var player = "El jugador 1"

    private var btn: Button? = null
    private var imageview: ImageView? = null
    private val GALLERY = 1
    private val CAMERA = 2

   public lateinit var ivImagen: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





       var imgFile: File =  File("/storage/emulated/0/AppPruebaDeDatos/Misty.jpg")

        if (imgFile.exists()){

            var miBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            var imagen: ImageView = findViewById(R.id.iv_player1)
            //imagen.setImageBitmap(miBitmap)

            Glide
                .with(this)
                .load(miBitmap)
                //.override(100,100)
                .centerCrop()
                .into(imagen)



           // iv_player1.setBackgroundColor(Color.parseColor("#2C2B2B"))

        }





        iv_player1.setOnClickListener(){

            player = "El jugador 1"



            Toast.makeText(this,"Se a seleccionado el Jugador 1", Toast.LENGTH_SHORT).show()

        }

        iv_player2.setOnClickListener(){

            player = "El jugador 2"

            Toast.makeText(this,"Se a seleccionado el Jugador 2", Toast.LENGTH_SHORT).show()

        }


        btn_buscar.setOnClickListener(){

            var intent = Intent(this, base1::class.java)

            startActivity(intent)

        }

        btn_imagen.setOnClickListener{



           // var intent = Intent(this, Seleccionar_Imagen::class.java)

            //startActivity(intent)

            val imagenAlertDialog  = LayoutInflater.from(this).inflate(R.layout.activity_seleccionar__imagen,null)
            val imagenBuilder = AlertDialog.Builder(this)
                .setView(imagenAlertDialog)
                .show()




            ivImagen = imagenAlertDialog.findViewById<ImageView>(R.id.ivImagen2)


            var imgFile: File =  File("/storage/emulated/0/AppPruebaDeDatos/Misty.jpg")

            if (imgFile.exists()){

                var miBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                var imagen: ImageView = findViewById(R.id.iv_player1)
                //imagen.setImageBitmap(miBitmap)

                Glide
                    .with(this)
                    .load(miBitmap)
                    //.override(100,100)
                    .centerCrop()
                    .into(ivImagen)



                // iv_player1.setBackgroundColor(Color.parseColor("#2C2B2B"))

            }

            imagenAlertDialog.btnCancelar.setOnClickListener(){
                val cerrar = imagenBuilder.dismiss()
                cerrar

            }




            imagenAlertDialog.btnImagen.setOnClickListener {

                      val cerrar = imagenBuilder.dismiss()
                      cerrar




                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                        PackageManager.PERMISSION_DENIED){

                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

                        requestPermissions(permissions, PERMISSION__CODE)

                    }else{

                        choosePhotoFromGallary()

                    }

                }

                else {

                    choosePhotoFromGallary()

                }

                



            }







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

        val txV1 = et_accion.text.toString()
        //val txV2 = et_descripcion.text.toString()

        if (txV1.trim().isEmpty()){

            Toast.makeText(this, "Favor de ingresar una Accion", Toast.LENGTH_LONG).show()

        }else{

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

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)



                    saveImage(bitmap)


                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@MainActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        /* else if (requestCode == CAMERA)
         {
             val thumbnail = data!!.extras!!.get("data") as Bitmap
             imageview!!.setImageBitmap(thumbnail)
             saveImage(thumbnail)
             Toast.makeText(this@SeleccionarImagen, "Image Saved!", Toast.LENGTH_SHORT).show()
         }*/
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.WEBP, 30, bytes)
        myBitmap
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + MainActivity.IMAGE_DIRECTORY
        )
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())

            /*  val f = File(wallpaperDirectory, ((Calendar.getInstance()
                  .getTimeInMillis()).toString() + "Misty.jpg"))*/
            val f = File(wallpaperDirectory, ("Misty.jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)

            if (f == null ){



            }
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {

        private val IMAGE_DIRECTORY = "/AppPruebaDeDatos"


        private val IMAGE_PICK_CODE = 1000

        val PERMISSION__CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){

            MainActivity.PERMISSION__CODE -> {
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    choosePhotoFromGallary()

                }else{

                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }




        }
    }





}
