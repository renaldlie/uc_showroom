package com.example.uc_showroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
    private lateinit var inputNama: TextInputLayout
    private lateinit var inputTelp: TextInputLayout
    private lateinit var inputIdcard: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pesanan)
        btnTambah = findViewById(R.id.btnTambah)
        inputNama = findViewById(R.id.namaCustomer)
        inputTelp = findViewById(R.id.notelpCustomer)
        inputIdcard = findViewById(R.id.idcardCustomer)

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
    }
}
