package com.example.uc_showroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uc_showroom.R
import com.example.uc_showroom.adapter.CustomerAdapter
import com.example.uc_showroom.helper.Const
import com.example.uc_showroom.model.CustomerData
import com.example.uc_showroom.model.CustomerResponse
import com.example.uc_showroom.retrofit.APIendpoint
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomerDetail : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerAdapter
    private lateinit var apiEndPoint: APIendpoint
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_detail)

        recyclerView = findViewById(R.id.rv_customerdetail)
        adapter = CustomerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiEndPoint = retrofit.create(APIendpoint::class.java)

        // Make a network request and update the adapter
        val requestBody = APIendpoint.ReadCustomer("?1")
        val call = apiEndPoint.readData( 3)


        call.enqueue(object : Callback<CustomerResponse> {
            override fun onResponse(call: Call<CustomerResponse>, response: Response<CustomerResponse>) {
                if (response.isSuccessful) {
                    val customerResponse = response.body()
                    val data = customerResponse?.data
                    data?.let {
                        adapter.setData(it)
                    }
                } else {
                    // Handle error response
                    Log.e("YourActivity", "Error: ${response.code()}")
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