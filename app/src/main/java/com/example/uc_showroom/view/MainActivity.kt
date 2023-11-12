package com.example.uc_showroom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.uc_showroom.R
import com.example.uc_showroom.helper.SSLUtils

class MainActivity : AppCompatActivity() {

    private lateinit var buttonTambah : Button
    private lateinit var buttonDetailPesanan : Button
    private lateinit var buttonDetailCustomer : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTambah = findViewById(R.id.btntambah)
        buttonDetailPesanan = findViewById(R.id.btnpesananDetail)
        buttonDetailCustomer = findViewById(R.id.btncustomerDetail)


        buttonTambah.setOnClickListener {
            SSLUtils.trustAllCertificates()
            val intent = Intent(this, TambahPesanan::class.java)
            startActivity(intent)
        }

        buttonDetailCustomer.setOnClickListener {
            SSLUtils.trustAllCertificates()
            val intent = Intent(this, CustomerDetail::class.java)
            startActivity(intent)
        }



        buttonDetailPesanan.setOnClickListener {

            val intent = Intent(this, DetailPesanan::class.java)
            startActivity(intent)
        }




    }
}