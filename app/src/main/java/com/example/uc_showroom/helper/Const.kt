package com.example.uc_showroom.helper

object Const {

    const val BASE_URL: String = "http://192.168.0.110/ucshowroom/"

    // Create Customer
    const val END_POINT_CREATE: String = "customer.php?api=signup"

    // Read Customer
    const val END_POINT_READ: String = "customer.php?api=read"

    // Update Customer
    const val END_POINT_UPDATE: String = "customer.php?api=update"

    // Delete Customer
    const val END_POINT_DELETE: String = "customer.php?api=delete&id="

    //Create Pesanan
    const val END_POINT_CREATE_PESANAN: String = "pesanan.php?api=create"

    //Read Pesanan
    const val END_POINT_READ_PESANAN: String = "pesanan.php?api=read"

    //Update Pesanan
    const val END_POINT_UPDATE_PESANAN: String = "pesanan.php?api=update"

    //Delete Pesanan
    const val END_POINT_DELETE_PESANAN: String = "customer.php?api=delete"

}