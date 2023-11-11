package com.example.uc_showroom.retrofit

import com.example.uc_showroom.helper.Const.END_POINT_GET
import com.example.uc_showroom.model.CustomerResponse
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIendpoint {

    @POST(END_POINT_GET)
    fun postData(@Body request: RequestCustomer): Call<CustomerResponse>


    data class RequestCustomer(
        @SerializedName("nama") val nama: String,
        @SerializedName("notelp") val notelp: String,
        @SerializedName("id_card") val idcard: String
    )
}