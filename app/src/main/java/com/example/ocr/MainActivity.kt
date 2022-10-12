package com.example.ocr

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
//    val  CAMERA_RQ = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart= findViewById(R.id.btnStart) as Button

        btnStart.setOnClickListener {
//            checkOnPermission(android.Manifest.permission.CAMERA,"Camera",CAMERA_RQ)
            val intent = Intent(this, TakeImage::class.java)
            startActivity(intent)

        }





    }

//
//
//    private fun  checkOnPermission(permission:String,name:String,requestCode:Int){
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            when{
//                ContextCompat.checkSelfPermission(applicationContext,permission)==PackageManager.PERMISSION_GRANTED ->{
//                    Toast.makeText(applicationContext,"$name permission granted",Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, ScannerActivity::class.java)
//                    startActivity(intent)
//                }
//                shouldShowRequestPermissionRationale(permission)-> showDialog(permission,name,requestCode)
//                else -> ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
//            }
//        }
//
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        fun innerCheck(name:String){
//            if (grantResults.isEmpty()|| grantResults[0] != PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(applicationContext,"$name permission refused",Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(applicationContext,"$name permission granted",Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, ScannerActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        when(requestCode){
//            CAMERA_RQ -> innerCheck("Camera")
//        }
//    }
//    private fun showDialog(permission: String,name: String,requestCode: Int){
//        val builder = AlertDialog.Builder(this)
//        builder.apply {
//            setMessage("Permission to access your $name is required to use this app")
//            setTitle("Permission Required")
//            setPositiveButton("OK"){dialog,which ->
//                ActivityCompat.requestPermissions(this@MainActivity,arrayOf(permission),requestCode)
//            }
//        }
//        val dialog = builder.create()
//        dialog.show()
//    }

    }

