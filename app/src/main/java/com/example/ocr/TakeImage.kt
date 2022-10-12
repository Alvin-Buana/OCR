package com.example.ocr

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File

class TakeImage : AppCompatActivity() {

    private lateinit var cameraBtn: Button
    private lateinit var myImage: ImageView
    private val FILE_NAME = "Photo.jpg"
    private val cameraRequestId  = 1222
    private val storageRequestId = 1223
    private lateinit var photoFile :File
    private lateinit var recognizedTextEt : EditText
    private lateinit var textRecognizer: TextRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_image)

        cameraBtn = findViewById(R.id.cameraBtn)
        myImage = findViewById(R.id.myImage)
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        recognizedTextEt = findViewById(R.id.recognizedTextEt)
        //melakukan permission
        if (ContextCompat.checkSelfPermission(
                applicationContext, Manifest.permission.CAMERA
            )== PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                cameraRequestId
            )
//        if (ContextCompat.checkSelfPermission(
//                applicationContext, Manifest.permission.CAMERA
//            )== PackageManager.PERMISSION_DENIED)
//            ActivityCompat.requestPermissions(
//                this, arrayOf(Manifest.permission.CAMERA),
//                cameraRequestId
//            )
     // camerannya di buka
        cameraBtn.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile=getPhotoFile(FILE_NAME)
            val fileProvider = FileProvider.getUriForFile(this,"com.example.fileprovider",photoFile)
            cameraInt.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider)
            startActivityForResult(cameraInt,cameraRequestId)// tidak apapap funtionnya dicoret masih bsa jalan itu bawaan dri androidnya functionnya

        }
    }

    private fun getPhotoFile(fileName: String): File {
            val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)// untuk meletakan photosnya
            return File.createTempFile(fileName,".jpg",storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestId){
            //menyimpan image di layout -> di activitynya
//            val images: Bitmap = data?.extras?.get("data") as Bitmap

            val takeImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            val preImage = InputImage.fromBitmap(takeImage, 90)
            myImage.setImageBitmap(takeImage)
            if(myImage== null){
                Toast.makeText(this,"No Image....",Toast.LENGTH_SHORT).show()
            }else{
                try{
                    val textTaskResult = textRecognizer.process(preImage)
                        .addOnSuccessListener { text->
                                val recognizedText = text.text
                                recognizedTextEt.setText(recognizedText)

                        }
                        .addOnFailureListener{e->
                            Toast.makeText(this,"Failed to give result because ${e.message}",Toast.LENGTH_SHORT).show()
                        }
                }catch (e:Exception){
                    Toast.makeText(this,"Failed to process because ${e.message}",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}