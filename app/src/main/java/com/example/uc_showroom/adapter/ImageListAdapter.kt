package com.example.uc_showroom.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.uc_showroom.R
import com.squareup.picasso.Picasso
import java.io.File

class ImageListAdapter(private val context: Context, private val list: List<Uri>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val inflater = LayoutInflater.from(context)
        convertView = inflater.inflate(R.layout.item_image, null)
        val imageView = convertView.findViewById<ImageView>(R.id.image)
        val file = File(list[position].path)
        Picasso.get().load(file).into(imageView)
        return convertView
    }
}

