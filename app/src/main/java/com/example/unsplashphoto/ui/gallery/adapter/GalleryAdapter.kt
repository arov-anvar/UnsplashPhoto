package com.example.unsplashphoto.ui.gallery.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.loadImage

const val COLLECTION_ID = "id"

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var items = mutableListOf<GalleryItem>()

    fun setItems(galleryItems: MutableList<GalleryItem>) {
        this.items = galleryItems
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val galleryImg : ImageView = itemView.findViewById(R.id.galleryImg)
        private val collectionNameTxt : TextView = itemView.findViewById(R.id.collectionNameTxt)
        private val itemCountTxt : TextView = itemView.findViewById(R.id.itemCountTxt)

        @SuppressLint("SetTextI18n")
        fun bind(item: GalleryItem) {
            galleryImg.loadImage(item.photo)
            collectionNameTxt.text = item.title
            itemCountTxt.text = "${item.itemCount} фотографий"

            itemView.setOnClickListener{
                val args = Bundle().apply {
                    putInt(COLLECTION_ID, item.id)
                }
                itemView.findNavController().navigate(R.id.collectionFragment, args)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val galleryView = LayoutInflater.from(parent.context).inflate(R.layout.collection_row, parent, false)
        return GalleryViewHolder(galleryView)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val galleryItem = items[position]
        holder.bind(galleryItem)
    }
}