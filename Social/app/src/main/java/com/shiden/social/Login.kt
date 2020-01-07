package com.shiden.social

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ivImagePerson.setOnClickListener(View.OnClickListener {

            checkPermision()

        })



    }

    val READIMAGE:Int = 253

    fun checkPermision(){

        if(Build.VERSION.SDK_INT>=23){

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=
                    PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf( android.Manifest.permission.READ_EXTERNAL_STORAGE),READIMAGE)
                return

            }


        }

        loadImage()

    }

    val PICK_IMAGE_CODE = 123

    fun loadImage(){

        var intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(intent,PICK_IMAGE_CODE )


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_CODE && resultCode == Activity.RESULT_OK && data != null ){


            val selectedImage=data.data!!
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectedImage,filePathColumn,null,null,null)
            cursor?.moveToFirst()
            val columnIndex=cursor?.getColumnIndex(filePathColumn[0])
            val picturePath= cursor?.getString(columnIndex!!)
            cursor?.close()
            ivImagePerson.setImageBitmap(BitmapFactory.decodeFile(picturePath))

        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){

            READIMAGE->{

                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    loadImage()

                }else{

                    Toast.makeText(applicationContext, "No se puede ingresar a tus imagenes", Toast.LENGTH_SHORT).show()

                }

            }

            else-> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }


    }

    fun buLogin(view: View){



    }

}
