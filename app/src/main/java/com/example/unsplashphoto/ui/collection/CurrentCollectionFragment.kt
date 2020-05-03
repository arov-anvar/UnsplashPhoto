package com.example.unsplashphoto.ui.collection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplashphoto.MainActivity
import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.mapToCollectionItems
import kotlinx.android.synthetic.main.current_collection_fragment.*

class CurrentCollectionFragment : Fragment() {
    private val currentAdapter = CurrentAdapter()
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
        currentAdapter.setListener(activity as MainActivity)

        with(imageRecycler) {
            adapter = currentAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val id = arguments!!.getInt("id")
        viewModel.getCollection(id, 1, 30).observe(this, Observer {
            currentProgressBar.visibility = View.GONE
            imageRecycler.visibility = View.VISIBLE
            currentAdapter.setItems(it.mapToCollectionItems())
        })

        imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

}