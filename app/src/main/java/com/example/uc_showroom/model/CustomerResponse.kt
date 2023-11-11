package com.example.uc_showroom.model

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    val data: CustomerData
)

data class CustomerData(
    val id_customer: Int,
    val nama: String,
    val notelp: Long,
    val id_card: Long
)

