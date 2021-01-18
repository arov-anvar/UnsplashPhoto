package com.anvar.unsplashphoto.ui.collection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import com.anvar.unsplashphoto.mapToCollectionItems
import com.anvar.unsplashphoto.ui.collection.adapter.CurrentAdapter
import com.anvar.unsplashphoto.ui.user.RecyclerViewPaginator
import kotlinx.android.synthetic.main.current_collection_fragment.*
import javax.inject.Inject

class CurrentCollectionFragment : Fragment(R.layout.current_collection_fragment) {

    private lateinit var viewModel : CurrentCollectionViewModel
    private val currentAdapter = CurrentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentCollectionViewModel::class.java)

        imageRecycler.apply {
            adapter = currentAdapter
            layoutManager = LinearLayoutManager(context)

            addOnScrollListener(object : RecyclerViewPaginator(this) {
                override fun loadMore() {
                    viewModel.nexPage()
                }
            })
        }

        val id = arguments?.getInt("id", 0) ?: 0
        viewModel.apply {
            if (mIdCollection == 0) mIdCollection = id
        }
        viewModel.collection.observe(this, Observer {
            currentProgressBar.visibility = View.GONE
            imageRecycler.visibility = View.VISIBLE
            currentAdapter.setItems(it.mapToCollectionItems())
        })


    }
}