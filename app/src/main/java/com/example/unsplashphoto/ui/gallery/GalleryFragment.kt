package com.example.unsplashphoto.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.gallery.adapter.GalleryAdapter
import com.example.unsplashphoto.ui.mapToGalleryItem
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {

    private val galleryAdapter = GalleryAdapter()
    private val linearLayoutManager = LinearLayoutManager(context)

    private lateinit var viewModel: UnsplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

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

