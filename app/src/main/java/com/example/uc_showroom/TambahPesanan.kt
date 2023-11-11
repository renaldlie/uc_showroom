package com.example.uc_showroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.uc_showroom.helper.Const.BASE_URL
import com.example.uc_showroom.helper.SSLUtils
import com.example.uc_showroom.model.CustomerResponse
import com.example.uc_showroom.retrofit.APIendpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Import statements...

class TambahPesanan : AppCompatActivity() {
    private lateinit var apiEndPoint: APIendpoint
    private lateinit var btnTambah: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pesanan)
        btnTambah = findViewById(R.id.btnTambah)
        SSLUtils.trustAllCertificates()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiEndPoint = retrofit.create(APIendpoint::class.java)

        btnTambah.setOnClickListener {
            val requestBody = APIendpoint.RequestCustomer("Halo", "0182312321", "123456")
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
