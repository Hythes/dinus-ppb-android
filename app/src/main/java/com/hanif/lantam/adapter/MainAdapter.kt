package com.hanif.lantam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanif.lantam.R
import com.hanif.lantam.model.BeritaDataModel
import com.hanif.lantam.model.BeritaSingleModel
import com.squareup.picasso.Picasso

class MainAdapter (var berita: ArrayList<BeritaDataModel>?) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var onItemClick : ((BeritaDataModel?)-> Unit)? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val judul: TextView = itemView.findViewById(R.id.judul)
        val isi: TextView = itemView.findViewById(R.id.berita)
        val foto: ImageView = itemView.findViewById(R.id.foto_image)
        val rt : LinearLayout = itemView.findViewById(R.id.LL_adapter)
        fun clear() {
            judul.text = ""
            isi.text = ""
            foto.setImageDrawable(null)

        }
        init{
            rt.setOnClickListener { onItemClick?.invoke(berita?.get(adapterPosition))  }

        }

        fun bind(item: BeritaDataModel?) {
            judul.text = item?.judul ?: ""
            isi.text = item?.isi ?: ""
            Picasso.get().load(item?.foto).into(foto)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_berita, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.clear()
        holder.bind(berita?.get(position))

    }

    override fun getItemCount(): Int {
        return berita?.size ?: 0
    }
}