package com.anvar.unsplashphoto.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import com.anvar.unsplashphoto.ui.gallery.adapter.GalleryAdapter
import com.anvar.unsplashphoto.mapToGalleryItem
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment(R.layout.gallery_fragment) {

    private val galleryAdapter = GalleryAdapter()
    private lateinit var viewModel: UnsplashViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)

        with(galleryRecycler) {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        loadGalleryItems(1)
    }

    private fun loadGalleryItems(page: Int) {
        viewModel.getCollections(page, 10).observe(viewLifecycleOwner, Observer {collections ->
            galleryRecycler.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            galleryAdapter.setItems(collections.mapToGalleryItem())
        })
    }

}

