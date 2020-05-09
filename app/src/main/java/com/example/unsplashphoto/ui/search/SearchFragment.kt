package com.example.unsplashphoto.ui.search

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphoto.ui.MainActivity
import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.search.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment() {
    private lateinit var viewModel: UnsplashViewModel
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private val photoAdapter = PhotoAdapter()

    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)

        with(resultsRecycler) {
            adapter = photoAdapter
            layoutManager = LinearLayoutManager(context)
        }

        txtMessage.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_picture_menu, menu)
        searchView = menu.findItem(R.id.search_action).actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressBarSearch.visibility = View.VISIBLE
                Toast.makeText(context, "Query: $query", Toast.LENGTH_SHORT).show()
                viewModel.fetchPhotosByQuery(query.toString(), 1).observe(this@SearchFragment, Observer {
                    if(it.results.isNotEmpty()) {
                        photoAdapter.setPhotos(it.results)
                        resultsRecycler.visibility = View.VISIBLE
                        progressBarSearch.visibility = View.GONE
                        txtMessage.visibility = View.GONE
                    } else {
                        resultsRecycler.visibility = View.GONE
                        txtMessage.visibility = View.VISIBLE
                        progressBarSearch.visibility = View.GONE
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return super.onCreateOptionsMenu(menu, menuInflater)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)

        resultsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }


}
