package com.example.uc_showroom.model

import android.os.Parcel
import android.os.Parcelable


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
    val nama: String?,
    val notelp: String?,
    val id_card: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_customer)
        parcel.writeString(nama)
        parcel.writeString(notelp)
        parcel.writeString(id_card)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomerData> {
        override fun createFromParcel(parcel: Parcel): CustomerData {
            return CustomerData(parcel)
        }

        override fun newArray(size: Int): Array<CustomerData?> {
            return arrayOfNulls(size)
        }
    }
}

data class Customer(
    val id_customer: Int,
    val nama: String,
    val notelp: String, // This could be Int or String based on your actual data
    val id_card: String, // This could be Int or String based on your actual data
    val img: String
)
