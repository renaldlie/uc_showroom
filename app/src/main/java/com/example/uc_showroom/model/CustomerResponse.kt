package com.example.uc_showroom.model

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    val data: List<CustomerData>,
    val id_customer: Int,
    val nama: String,
    val notelp: Long,
    val id_card: Long
)

data class CustomerDataResponse(
    val status: Int,
    val message: String,
    val data: List<CustomerData>,
    val id_customer: Int,
    val nama: String,
    val notelp: Long,
    val id_card: String,
    val img: String
)

data class CustomerData(
    val id_customer: Int,
    val nama: String,
    val notelp: Long,
    val id_card: Long
)

data class Customer(
    val id_customer: Int,
    val nama: String,
    val notelp: Any, // This could be Int or String based on your actual data
    val id_card: Any, // This could be Int or String based on your actual data
    val img: String
)
