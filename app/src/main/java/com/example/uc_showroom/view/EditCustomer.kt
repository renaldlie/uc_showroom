package com.example.uc_showroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.uc_showroom.R
import com.example.uc_showroom.model.CustomerData
import com.google.android.material.textfield.TextInputLayout

class EditCustomer : AppCompatActivity() {

    private lateinit var editnamaCustomer: EditText
    private lateinit var editnotelpCustomer: EditText
    private lateinit var editidkartuCustomer: EditText
    private lateinit var btneditSelesai: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_customer)
        editnamaCustomer = findViewById(R.id.editnamadataCustomer)
        editnotelpCustomer = findViewById(R.id.editnotelpdataCustomer)
        editidkartuCustomer = findViewById(R.id.editidkartudataCustomer)


        val customerData = intent.getParcelableExtra<CustomerData>("customerData")

        // Now you have the customerData object, and you can use its properties as needed
        if (customerData != null) {
            // Access customerData properties
            val customerId = customerData.id_card
            val customerName = customerData.nama
            val customerPhone = customerData.notelp

            editnamaCustomer.text?.append(customerName)
            editnotelpCustomer.text?.append(customerPhone)
            editidkartuCustomer.text?.append(customerId)



            // Use the data as needed in your EditCustomer activity
        } else {
            Log.e("EditCustomer", "Error: Unable to retrieve customerData from intent")
        }

        btneditSelesai.setOnClickListener{

        }
    }
}