package com.example.unsplashphoto.ui.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphoto.R
import com.example.unsplashphoto.loadImage

class CurrentAdapter : RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>() {
    private val currentItems = mutableListOf<CurrentItem>()
    private lateinit var listener: CurrentItemClickListener

    fun setListener(listener: CurrentItemClickListener) {
        this.listener = listener
    }

    fun setItems(currentItems: MutableList<CurrentItem>) {
        this.currentItems.clear()
        this.currentItems.addAll(currentItems)
        notifyDataSetChanged()
    }

    inner class CurrentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val currentImageView: ImageView = itemView.findViewById(R.id.galleryImageView)

        fun bind(currentItem: CurrentItem) {
            currentImageView.loadImage(currentItem.photoUrl)
            itemView.setOnClickListener {
                listener.onCurrentItemSelected(currentItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        val currentImageView =
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_row, parent, false)
        return CurrentViewHolder(currentImageView)
    }

    override fun getItemCount(): Int = currentItems.count()

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        val item = currentItems[position]
        holder.bind(item)
    }

    interface CurrentItemClickListener {
        fun onCurrentItemSelected(id: String)
    }
}