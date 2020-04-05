package com.example.unsplashphoto.ui.gallery

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.gallery.adapter.GalleryAdapter
import com.example.unsplashphoto.ui.gallery.adapter.GalleryItem
import com.example.unsplashphoto.ui.mapToGalleryItem
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {

    private val galleryAdapter = GalleryAdapter()

    companion object {
        fun newInstance() =
            GalleryFragment()
    }

    private lateinit var viewModel: UnsplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UnsplashViewModel::class.java)

        with(galleryRecycler) {
            adapter = galleryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}

