package com.anvar.unsplashphoto.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.loadImage
import kotlinx.android.synthetic.main.user_image_item.view.*

class UserImagesAdapter(private val mData: List<UserImageItem>) : RecyclerView.Adapter<UserImagesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_image_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoImageView = holder.itemView.photoImageView
        photoImageView.loadImage(mData[position].imgSrc)
        photoImageView.setOnClickListener {
            val args = bundleOf("photoId" to mData[position].id)
            holder.itemView.findNavController().navigate(R.id.photoFragment, args)
        }
    }

    override fun getItemCount(): Int = mData.size
}