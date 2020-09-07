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
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import com.anvar.unsplashphoto.mapToCollectionItems
import com.anvar.unsplashphoto.ui.collection.adapter.CurrentAdapter
import kotlinx.android.synthetic.main.current_collection_fragment.*

class CurrentCollectionFragment : Fragment() {
    private val currentAdapter =
        CurrentAdapter()
    private lateinit var viewModel : UnsplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_collection_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)

        with(imageRecycler) {
            adapter = currentAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val id = arguments?.getInt("id", 0) ?: 0
        viewModel.getCollection(id, 1, 30).observe(this, Observer {
            currentProgressBar.visibility = View.GONE
            imageRecycler.visibility = View.VISIBLE
            currentAdapter.setItems(it.mapToCollectionItems())
        })
    }

}