package com.example.unsplashphoto.ui.collection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.loadImage

class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {
    private val pictures = mutableListOf<CollectionItem>()

    fun setPitures(pictures: MutableList<CollectionItem>) {
        pictures.clear()
        pictures.addAll(pictures)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pictureImageView: ImageView = itemView.findViewById(R.id.galleryItem)

        fun bind(item: CollectionItem) {
            pictureImageView.loadImage(item.photoUrl.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val picturesView = LayoutInflater.from(parent.context).inflate(R.layout.gallery_row, parent, false)
        return ViewHolder(picturesView)
    }

    override fun getItemCount() = pictures.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = pictures[position]
        holder.bind(picture)
    }
}
