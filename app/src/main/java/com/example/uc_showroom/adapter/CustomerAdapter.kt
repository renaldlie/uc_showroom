package com.example.uc_showroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uc_showroom.R
import com.example.uc_showroom.model.CustomerData

class CustomerAdapter(private val context: Context, private val editClickListener: OnEditClickListener) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    private var dataList: List<CustomerData> = emptyList()
    interface OnEditClickListener {
        fun onEditClick(customerData: CustomerData)
    }
    // ViewHolder class holds references to the views in each list item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameCustomer: TextView = itemView.findViewById(R.id.namadataCustomer)
        val notelpCustomer: TextView = itemView.findViewById(R.id.notelpdataCustomer)
        val idkartuCustomer: TextView = itemView.findViewById(R.id.idkartudataCustomer)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
    }

    // Creates new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cv_customer, parent, false)
        return ViewHolder(view)
    }

    // Binds the data to the TextView in each list item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = dataList[position]
        holder.nameCustomer.text = customer.nama.toString()
        holder.notelpCustomer.text = customer.notelp.toString()
        holder.idkartuCustomer.text = customer.id_card.toString()
        holder.btnEdit.setOnClickListener {
            editClickListener.onEditClick(customer)
        }
    }

    // Returns the size of the dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(dataList: List<CustomerData>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}
