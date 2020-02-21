package com.example.unsplashphoto.ui.gallery

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.gallery.adapter.GalleryAdapter
import com.example.unsplashphoto.ui.gallery.adapter.GalleryItem
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {

    private val galleryAdapter = GalleryAdapter()

    companion object {
        fun newInstance() =
            GalleryFragment()
    }

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)

        with(galleryRecycler) {
            adapter = galleryAdapter
            layoutManager = LinearLayoutManager(context)
        }

        galleryAdapter.setItems(mock())
    }

    fun mock(): MutableList<GalleryItem> {
        val items = mutableListOf<GalleryItem>()
        items.add(GalleryItem(R.drawable.space, "Space", 10))
        items.add(GalleryItem(R.drawable.space, "Space", 20))
        items.add(GalleryItem(R.drawable.space, "Space", 40))
        items.add(GalleryItem(R.drawable.space, "Space", 89))
        items.add(GalleryItem(R.drawable.space, "Space", 15))
        return items
    }

}

