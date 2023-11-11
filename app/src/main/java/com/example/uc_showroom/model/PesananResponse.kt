package com.example.uc_showroom.model

data class PesananResponse (
    val data: PesananData
)

data class PesananData(
    val id_customer: Int,
    val nama: String,
    val notelp: Long,
    val id_card: Long
)