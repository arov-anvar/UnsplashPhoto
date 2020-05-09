package com.example.unsplashphoto.ui.gallery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphoto.R
import com.example.unsplashphoto.loadImage


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var items = mutableListOf<Collection>()

    fun setItems(galleryItems: MutableList<Collection>) {
        this.items.clear()
        this.items.addAll(galleryItems)
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val galleryImg : ImageView = itemView.findViewById(R.id.galleryImg)
        private val collectionNameTxt : TextView = itemView.findViewById(R.id.collectionNameTxt)
        private val itemCountTxt : TextView = itemView.findViewById(R.id.itemCountTxt)

        @SuppressLint("SetTextI18n")
        fun bind(item: Collection) {
            galleryImg.loadImage(item.photo)
            collectionNameTxt.text = item.title
            itemCountTxt.text = "${item.itemsCount} фотографий"

            itemView.setOnClickListener{
                val args = bundleOf("id" to item.id)
                itemView.findNavController().navigate(R.id.currentCollectionFragment, args)
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