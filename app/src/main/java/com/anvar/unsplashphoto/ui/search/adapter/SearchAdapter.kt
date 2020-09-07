package com.anvar.unsplashphoto.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.loadImage
import com.anvar.unsplashphoto.model.entity.search.Search

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.PhotoViewHolder>() {

    private var data = mutableListOf<Search>()

    fun setData(photos: List<Search>) {
        this.data.clear()
        this.data.addAll(photos)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photoImageView: ImageView = itemView.findViewById(R.id.imageViewPhoto)

        fun bind(search: Search) {
            photoImageView.loadImage(search.urlPhoto)

            photoImageView.setOnClickListener {
                when (search.typeSearch) {
                    1 -> {
                        val args = bundleOf("photoId" to search.id)
                        itemView.findNavController().navigate(R.id.photoFragment, args)
                    }
                    2 -> {
                        val args = bundleOf("id" to search.id.toInt())
                        itemView.findNavController().navigate(R.id.currentCollectionFragment, args)
                    }
                    3 -> {
                        val args = bundleOf("userName" to search.id)
                        itemView.findNavController().navigate(R.id.userFragment, args)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val photoView = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return PhotoViewHolder(photoView)
    }

    override fun getItemCount() = data.count()

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val value = data[position]
        holder.bind(value)
    }
}