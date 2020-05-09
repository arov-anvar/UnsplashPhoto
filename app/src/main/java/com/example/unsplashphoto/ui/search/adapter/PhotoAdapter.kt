package com.example.unsplashphoto.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphoto.R
import com.example.unsplashphoto.data.search.Result
import com.example.unsplashphoto.ui.loadImage

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var photos = mutableListOf<Result>()

    fun setPhotos(photos: List<Result>) {
        this.photos.clear()
        this.photos.addAll(photos)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photoImageView: ImageView = itemView.findViewById(R.id.imageViewPhoto)

        fun bind(photo: Result) {
            photoImageView.loadImage(photo.urls.full)

            photoImageView.setOnClickListener {
                val args = bundleOf("photoId" to photo.id)
                itemView.findNavController().navigate(R.id.photoFragment, args)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val photoView = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return PhotoViewHolder(photoView)
    }

    override fun getItemCount() = photos.count()

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }
}