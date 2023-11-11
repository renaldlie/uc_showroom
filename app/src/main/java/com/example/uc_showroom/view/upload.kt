package com.example.uc_showroom.view

import com.example.uc_showroom.R

import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*
import javax.net.ssl.HttpsURLConnection

class upload : AppCompatActivity() {
    private lateinit var GetImageFromGalleryButton: Button
    private lateinit var UploadImageOnServerButton: Button
    private lateinit var ShowSelectedImage: ImageView
    private lateinit var imageName: EditText
    private lateinit var FixBitmap: Bitmap
    private val ImageTag = "image_tag"
    private val ImageName = "image_data"
    private lateinit var progressDialog: ProgressDialog
    private lateinit var byteArrayOutputStream: ByteArrayOutputStream
    private lateinit var byteArray: ByteArray
    private lateinit var ConvertImage: String
    private lateinit var GetImageNameFromEditText: String
    private lateinit var httpURLConnection: HttpURLConnection
    private lateinit var url: URL
    private lateinit var outputStream: OutputStream
    private lateinit var bufferedWriter: BufferedWriter
    private var RC = 0
    private lateinit var bufferedReader: BufferedReader
    private lateinit var stringBuilder: StringBuilder
    private var check = true
    private val GALLERY = 1
    private val CAMERA = 2
    private val RESULT_CANCELED = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload2)
        GetImageFromGalleryButton = findViewById(R.id.buttonSelect)
        UploadImageOnServerButton = findViewById(R.id.buttonUpload)
        ShowSelectedImage = findViewById(R.id.imageView)
        imageName = findViewById(R.id.imageName)
        byteArrayOutputStream = ByteArrayOutputStream()
        GetImageFromGalleryButton.setOnClickListener(View.OnClickListener { showPictureDialog() })
        UploadImageOnServerButton.setOnClickListener(View.OnClickListener {
            GetImageNameFromEditText = imageName.text.toString()
            UploadImageToServer()
        })
        if (ContextCompat.checkSelfPermission(
                this@upload,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    5
                )
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf(
            "Photo Gallery",
            "Camera"
        )
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog: DialogInterface?, which: Int ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == this.RESULT_CANCELED) {
            return
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    FixBitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    ShowSelectedImage.setImageBitmap(FixBitmap)
                    UploadImageOnServerButton.visibility = View.VISIBLE
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(MainActivity@ this, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMERA) {
            FixBitmap = data!!.extras!!["data"] as Bitmap
            ShowSelectedImage.setImageBitmap(FixBitmap)
            UploadImageOnServerButton.visibility = View.VISIBLE
        }
    }

    fun UploadImageToServer() {
        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream)
        byteArray = byteArrayOutputStream.toByteArray()
        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT)
        object : AsyncTask<Void?, Void?, String?>() {
            override fun onPreExecute() {
                super.onPreExecute()
                progressDialog =
                    ProgressDialog.show(this@upload, "Image is Uploading", "Please Wait", false, false)
            }

            override fun onPostExecute(string1: String?) {
                super.onPostExecute(string1)
                progressDialog.dismiss()
                Toast.makeText(this@upload, string1, Toast.LENGTH_LONG).show()
            }

            override fun doInBackground(vararg params: Void?): String? {
                val imageProcessClass = ImageProcessClass()
                val HashMapParams: MutableMap<String, String> = HashMap()
                HashMapParams[ImageTag] = GetImageNameFromEditText
                HashMapParams[ImageName] = ConvertImage
                val FinalData = imageProcessClass.ImageHttpRequest("http:", HashMapParams)
                return FinalData
            }
        }.execute()
    }

    inner class ImageProcessClass {
        fun ImageHttpRequest(requestURL: String, PData: MutableMap<String, String>): String {
            stringBuilder = StringBuilder()
            try {
                url = URL(requestURL)
                httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.readTimeout = 20000
                httpURLConnection.connectTimeout = 20000
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doInput = true
                httpURLConnection.doOutput = true
                outputStream = httpURLConnection.outputStream
                bufferedWriter = BufferedWriter(
                    OutputStreamWriter(
                        outputStream,
                        "UTF-8"
                    )
                )
                bufferedWriter.write(bufferedWriterDataFN(PData))
                bufferedWriter.flush()
                bufferedWriter.close()
                outputStream.close()
                RC = httpURLConnection.responseCode
                if (RC == HttpsURLConnection.HTTP_OK) {
                    bufferedReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                    stringBuilder = StringBuilder()
                    var RC2: String?
                    while (bufferedReader.readLine().also { RC2 = it } != null) {
                        stringBuilder.append(RC2)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return stringBuilder.toString()
        }

        @Throws(UnsupportedEncodingException::class)
        private fun bufferedWriterDataFN(HashMapParams: MutableMap<String, String>): String {
            stringBuilder = StringBuilder()
            for ((key, value) in HashMapParams.entries) {
                if (check) check = false else stringBuilder.append("&")
                stringBuilder.append(URLEncoder.encode(key, "UTF-8"))
                stringBuilder.append("=")
                stringBuilder.append(URLEncoder.encode(value, "UTF-8"))
            }
            return stringBuilder.toString()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(
                    MainActivity@ this,
                    "Unable to use Camera..Please Allow us to use Camera",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}


