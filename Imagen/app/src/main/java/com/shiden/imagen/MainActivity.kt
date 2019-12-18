package com.shiden.imagen


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.*

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_pick_btn.setOnClickListener{

            if (Build.VERSION.SDK_INT >= VERSION_CODES.M){

                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                        PackageManager.PERMISSION_DENIED){

                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

                    requestPermissions(permissions, PERMISSION__CODE)

                }else{

                    pickImageFromGallery()

                }

            }

            else {

                pickImageFromGallery()

            }
        }

    }

    private fun pickImageFromGallery() {

        val intent = Intent(Intent.ACTION_PICK)
       intent.type = "image/ *"
        startActivityForResult(intent, IMAGE_PICK_CODE)

    }

    companion object {

        private val IMAGE_PICK_CODE = 1000

        private val PERMISSION__CODE = 1001

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){

            PERMISSION__CODE -> {
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    pickImageFromGallery()

                }else{

                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }




        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_view.setImageURI(data?.data)
        }
    }

}
