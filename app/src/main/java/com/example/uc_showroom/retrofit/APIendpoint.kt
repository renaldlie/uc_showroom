package com.example.uc_showroom.retrofit

import com.example.uc_showroom.helper.Const.END_POINT_CREATE
import com.example.uc_showroom.helper.Const.END_POINT_DELETE
import com.example.uc_showroom.helper.Const.END_POINT_UPDATE
import com.example.uc_showroom.model.CustomerDataResponse
import com.example.uc_showroom.model.CustomerResponse
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface APIendpoint {





    // Create Customer
    @POST(END_POINT_CREATE)
    fun postData(@Body request: RequestCustomer): Call<CustomerResponse>
    // Read Customer
    @GET("customer.php?api=read")
    fun readData(@Query("id") id: Int): Call<CustomerDataResponse>
    // Update Customer
    @POST(END_POINT_UPDATE)
    suspend fun updateData(): List<CustomerResponse>
    //Delete Customer
    @POST(END_POINT_DELETE)
    fun deleteData(@Body request: DeleteCustomer): Call<CustomerResponse>



    data class RequestCustomer(
        @SerializedName("nama") val nama: String,
        @SerializedName("notelp") val notelp: String,
        @SerializedName("id_card") val idcard: String
    )

    data class ReadCustomer(
        @SerializedName("id") val id: String,

    )

    data class updateCustomer(
        @SerializedName("id_customer") val id_customer: Int,
        @SerializedName("new_nama") val new_nama: String,
        @SerializedName("new_notelp") val new_notelp: String,
        @SerializedName("new_idcard") val new_idcard: String,

        )
    data class DeleteCustomer(
        @SerializedName("id_customer") val id_customer: Int,

    )
}