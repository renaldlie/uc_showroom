package com.example.uc_showroom.retrofit

import android.widget.EditText
import com.example.uc_showroom.helper.Const.END_POINT_CREATE
import com.example.uc_showroom.helper.Const.END_POINT_CREATE_PESANAN
import com.example.uc_showroom.helper.Const.END_POINT_DELETE
import com.example.uc_showroom.helper.Const.END_POINT_DELETE_PESANAN
import com.example.uc_showroom.helper.Const.END_POINT_READ_PESANAN
import com.example.uc_showroom.helper.Const.END_POINT_UPDATE
import com.example.uc_showroom.helper.Const.END_POINT_UPDATE_PESANAN
import com.example.uc_showroom.model.CustomerDataResponse
import com.example.uc_showroom.model.CustomerResponse
import com.example.uc_showroom.model.PesananData
import com.example.uc_showroom.model.PesananDataResponse
import com.example.uc_showroom.model.PesananResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface APIendpoint {

    // Create Customer
    @POST(END_POINT_CREATE)
    fun postData(@Body request: RequestCustomer): Call<CustomerResponse>
    // Read Customer
    @GET("customer.php?api=read")
    fun readData(@Query("id_customer") id_customer: Int): Call<CustomerDataResponse>
    // Update Customer
    @POST(END_POINT_UPDATE)
    suspend fun updateData(): List<CustomerResponse>
    //Delete Customer
    @POST(END_POINT_DELETE)
    fun deleteData(@Body request: DeleteCustomer): Call<CustomerResponse>

    // Create Pesanan
    @POST(END_POINT_CREATE_PESANAN)
    fun postPesanan(@Body request: RequestPesanan): Call<PesananData>

    // Read Pesanan
    @GET(END_POINT_READ_PESANAN)
    fun readPesanan(@Query("id_customer") id_customer: Int): Call<PesananDataResponse>

    // Update Pesanan
    @POST(END_POINT_UPDATE_PESANAN)
    suspend fun updatePesanan(): List<PesananResponse>

    //Delete Pesanan
    @DELETE(END_POINT_DELETE_PESANAN)
    fun deletePesanan(@Query("id_pesanan") id_pesanan: Int): Call<PesananResponse>



    data class RequestCustomer(
        @SerializedName("nama") val nama: String,
        @SerializedName("notelp") val notelp: String,
        @SerializedName("id_card") val idcard: String
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

            data class RequestPesanan(
                @SerializedName("id_kendaraan") val id_kendaraan: Int?,
                @SerializedName("jumlah") val jumlah: Int?,
                @SerializedName("totalbiaya") val total: Double?
            )

            data class ReadCustomer(
        @SerializedName("id") val id: String,

        )

            data class updatePesanan(
        @SerializedName("id_customer") val id_customer: Int,
        @SerializedName("new_nama") val new_nama: String,
        @SerializedName("new_notelp") val new_notelp: String,
        @SerializedName("new_idcard") val new_idcard: String,

        )
            data class DeletePesanan(
        @SerializedName("id_customer") val id_customer: Int,

        )
}