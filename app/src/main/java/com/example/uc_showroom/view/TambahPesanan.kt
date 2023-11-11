package com.example.uc_showroom.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.example.uc_showroom.R
import com.example.uc_showroom.helper.Const.BASE_URL
import com.example.uc_showroom.helper.SSLUtils
import com.example.uc_showroom.model.CustomerResponse
import com.example.uc_showroom.retrofit.APIendpoint
import com.google.android.material.textfield.TextInputLayout

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Import statements...

class TambahPesanan : AppCompatActivity() {
    private lateinit var apiEndPoint: APIendpoint
    private lateinit var btnTambah: Button
    private lateinit var btnUpload: Button
    private lateinit var inputNama: TextInputLayout
    private lateinit var inputTelp: TextInputLayout
    private lateinit var inputIdcard: TextInputLayout
    private lateinit var inputModel: TextInputLayout
    private lateinit var inputTahun: TextInputLayout
    private lateinit var inputJumlahpenumpang: TextInputLayout
    private lateinit var inputManufaktur: TextInputLayout
    private lateinit var inputHarga: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pesanan)


        btnTambah = findViewById(R.id.btnTambah)
        btnUpload = findViewById(R.id.btnUpload)
        inputNama = findViewById(R.id.namaCustomer)
        inputTelp = findViewById(R.id.notelpCustomer)
        inputIdcard = findViewById(R.id.idcardCustomer)
        inputModel = findViewById(R.id.modelKendaraan)
        inputTahun = findViewById(R.id.tahunKendaraan)
        inputJumlahpenumpang = findViewById(R.id.jumlahpenumpangKendaraan)
        inputManufaktur = findViewById(R.id.manufakturKendaraan)
        inputHarga = findViewById(R.id.hargaKendaraan)

        val autoComplete: AutoCompleteTextView = findViewById(R.id.input_jenis)

        val items = listOf("Mobil", "Motor", "Truck")

// Creating an ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)

// Setting the ArrayAdapter to the AutoCompleteTextView
        autoComplete.setAdapter(adapter)

// Setting up TextInputLayout
        val textInputLayout = findViewById<TextInputLayout>(R.id.jenisKendaraan)
        textInputLayout.isHintEnabled = true
        textInputLayout.hint = "Select an item"

// Optional: Set an item click listener for additional actions
        autoComplete.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position)
            // Do something with the selected item
        }

        SSLUtils.trustAllCertificates()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiEndPoint = retrofit.create(APIendpoint::class.java)

        btnTambah.setOnClickListener {
            val namaCustomer = inputNama.editText?.text.toString()
            val telpCustomer = inputTelp.editText?.text.toString()
            val idcardCustomer = inputIdcard.editText?.text.toString()
            val modelKendaraan = inputModel.editText?.text.toString()
            val tahunKendaraan = inputTahun.editText?.text.toString()
            val jumlahopenumpangKendaraan = inputJumlahpenumpang.editText?.text.toString()
            val manufakturKendaraan = inputManufaktur.editText?.text.toString()
            val hargaKendaraan = inputHarga.editText?.text.toString()


            val requestBody = APIendpoint.RequestCustomer("$namaCustomer", "$telpCustomer", "$idcardCustomer")
            val call = apiEndPoint.postData(requestBody)

            call.enqueue(object : Callback<CustomerResponse> {
                override fun onResponse(
                    call: Call<CustomerResponse>,
                    response: Response<CustomerResponse>
                ) {
                    if (response.isSuccessful) {
                        val customerResponse: CustomerResponse? = response.body()

                        // Handle the customerResponse here on the main thread if needed
                        runOnUiThread {

                            println("Success")
                            // Update UI here if needed
                        }
                    } else {
                        // Handle error response
                        println("Error: ${response.code()}")
                        // Handle error UI update if needed
                    }
                }

                override fun onFailure(call: Call<CustomerResponse>, t: Throwable) {
                    // Handle network failure
                    println("Network Failure: ${t.message}")
                    // Handle error UI update if needed
                }
            })
        }

        btnUpload.setOnClickListener {

        }




    }
}
