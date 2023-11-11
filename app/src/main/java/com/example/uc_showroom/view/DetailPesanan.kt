package com.example.uc_showroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uc_showroom.R
import com.example.uc_showroom.adapter.PesananAdapter
import com.example.uc_showroom.helper.Const
import com.example.uc_showroom.model.PesananDataResponse
import com.example.uc_showroom.model.PesananResponse
import com.example.uc_showroom.retrofit.APIendpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var recyclerView: RecyclerView
private lateinit var adapter: PesananAdapter
private lateinit var apiEndPoint: APIendpoint
class DetailPesanan : AppCompatActivity(), PesananAdapter.OnDeleteClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihatpesanan)

        recyclerView = findViewById(R.id.rv_pesanan)
        adapter = PesananAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiEndPoint = retrofit.create(APIendpoint::class.java)

        // Make a network request and update the adapter
        val call = apiEndPoint.readPesanan(3) // Check your API interface for the correct parameters
        call.enqueue(object : Callback<PesananDataResponse> {
            override fun onResponse(call: Call<PesananDataResponse>, response: Response<PesananDataResponse>) {
                if (response.isSuccessful) {
                    val pesananResponse = response.body()
                    val data = pesananResponse?.data
                    data?.let {
                        adapter.setpesananData(it)
                    }
                } else {
                    // Handle error response
                    Log.e("YourActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PesananDataResponse>, t: Throwable) {
                // Handle network failure
                Log.e("YourActivity", "Network Failure: ${t.message}")

                // Handle error UI update if needed
            }
        })

    }
    override fun onDeleteClick(position: Int) {
        // Handle delete operation here
        val pesananToDelete = adapter.getItem(position)

        // Perform the delete operation by calling your API endpoint
        val call = apiEndPoint.deletePesanan(api = "delete", id = pesananToDelete.id_pesanan)

        call.enqueue(object : Callback<PesananResponse> {
            override fun onResponse(call: Call<PesananResponse>, response: Response<PesananResponse>) {
                if (response.isSuccessful) {
                    // Handle successful deletion
                    // You might want to update your UI or take other actions
                    adapter.removeItem(position)
                } else {
                    // Handle error response
                    Log.e("YourActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PesananResponse>, t: Throwable) {
                // Handle network failure
                Log.e("YourActivity", "Network Failure: ${t.message}")
                // Handle error UI update if needed
            }
        })
    }
}