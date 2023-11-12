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
import com.example.uc_showroom.model.PesananData

class PesananAdapter(private val context: Context, private val deleteClickListener: OnDeleteClickListener) :
    RecyclerView.Adapter<PesananAdapter.ViewHolder>() {
    var dataList: MutableList<PesananData> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pesananID: TextView = itemView.findViewById(R.id.pesananID)
        val kendaraanID: TextView = itemView.findViewById(R.id.kendaraanID)
        val jumlahPesanan: TextView = itemView.findViewById(R.id.jumlahPesanan)
        val biayaPesanan: TextView = itemView.findViewById(R.id.biayaPesanan)
        val btnDelete: Button = itemView.findViewById(R.id.btndelPesan)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cv_detailpesanan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pesanan = dataList[position]
        holder.pesananID.text = pesanan.id_pesanan.toString()
        holder.kendaraanID.text = pesanan.id_kendaraan ?: "N/A"
        holder.jumlahPesanan.text = pesanan.jumlah.toString()
        holder.biayaPesanan.text = pesanan.total.toString()
        holder.btnDelete.setOnClickListener {
            deleteClickListener.onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getItem(position: Int): PesananData {
        return dataList[position]
    }

    fun setPesananData(dataList: List<PesananData>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()  // Consider using notifyDataSetChanged() for structural changes
    }
}
