package com.example.uc_showroom.model

data class PesananResponse (
    val data: List<PesananData>
)

data class PesananDataResponse(
    val data: List<PesananData>,
    val id_pesanan: Int,
    val id_kendaraan: String,
    val jumlah: Long,
    val total: Long
)

data class PesananData(
    val id_pesanan: Int,
    val id_kendaraan: String,
    val jumlah: Long,
    val total: Long
)