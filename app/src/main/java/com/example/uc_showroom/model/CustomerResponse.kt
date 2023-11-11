package com.example.uc_showroom.model

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    val data: List<CustomerData>,
    val id_customer: Int,
    val nama: String,
    val notelp: String,
    val id_card: String
)

data class CustomerDataResponse(
    val status: Int,
    val message: String,
    val data: List<CustomerData>,

)

data class CustomerData(
    val id_customer: Int,
    val nama: String,
    val notelp: String,
    val id_card: String
)

data class Customer(
    val id_customer: Int,
    val nama: String,
    val notelp: String, // This could be Int or String based on your actual data
    val id_card: String, // This could be Int or String based on your actual data
    val img: String
)
