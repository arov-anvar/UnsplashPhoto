package com.example.unsplashphoto.ui.collection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphoto.R
import com.example.unsplashphoto.loadImage

class CurrentAdapter : RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>() {
    private val currentCollection = mutableListOf<CurrentItem>()
    private lateinit var listener: CurrentItemClickListener

    inner class CurrentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val currentImageView: ImageView = itemView.findViewById(R.id.galleryImageView)

        fun bind(currentItem: CurrentItem) {
            currentImageView.loadImage(currentItem.photoUrl)
            itemView.setOnClickListener {
                val args = bundleOf("photoId" to currentItem.id)
                itemView.findNavController().navigate(R.id.photoFragment, args)
            }
        }
    }

    fun setItems(currentItems: MutableList<CurrentItem>) {
        this.currentCollection.clear()
        this.currentCollection.addAll(currentItems)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        val coverCollection =
            LayoutInflater.from(parent.context).inflate(R.layout.cover_collection, parent, false)
        return CurrentViewHolder(coverCollection)
    }

    override fun getItemCount(): Int = currentCollection.count()

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        val item = currentCollection[position]
        holder.bind(item)
    }

    interface CurrentItemClickListener {
        fun onCurrentItemSelected(id: String)
    }
}