/*package com.shiden.pruebabasededatos

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_seleccionar_imagen.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import com.bumptech.glide.Glide



class SeleccionarImagen : AppCompatActivity() {

    private var btn: Button? = null
    private var imageview: ImageView? = null
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_imagen)

        var imgFile:File =  File("/demonuts/Misty.jpg")

        if (imgFile.exists()){
            var miBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            var imagen:ImageView = findViewById(R.id.iv_player1)
          //  imagen.setImageBitmap(miBitmap)


        }


        btn = findViewById<View>(R.id.btnImagen) as Button
        imageview = findViewById<View>(R.id.iv) as ImageView

        btn!!.setOnClickListener {


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

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

        fun choosePhotoFromGallary() {
            val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(galleryIntent, GALLERY)
        }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
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

                   // Toast.makeText(this@SeleccionarImagen, "Image Saved! ${path.toString()}", Toast.LENGTH_LONG).show()
                    //imageview!!.setImageBitmap(bitmap)

                    Glide
                        .with(this)
                        .load(bitmap)
                        // .override(100,100)
                        .centerCrop()
                        .into(iv)
                    saveImage(bitmap)


                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@SeleccionarImagen, "Failed!", Toast.LENGTH_SHORT).show()
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
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
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

        internal val IMAGE_DIRECTORY = "/demonuts"


        private val IMAGE_PICK_CODE = 1000

        val PERMISSION__CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){

            PERMISSION__CODE -> {
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    choosePhotoFromGallary()

                }else{

                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }




        }
    }


}*/


