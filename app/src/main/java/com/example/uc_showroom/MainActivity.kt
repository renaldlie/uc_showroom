package com.example.uc_showroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.uc_showroom.helper.SSLUtils
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class MainActivity : AppCompatActivity() {

    private lateinit var buttonTambah : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTambah = findViewById(R.id.btn_tambah)

        buttonTambah.setOnClickListener {
            SSLUtils.trustAllCertificates()
            val intent = Intent(this, TambahPesanan::class.java)
            startActivity(intent)
        }




    }
}