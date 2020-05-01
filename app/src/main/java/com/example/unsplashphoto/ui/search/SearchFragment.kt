package com.example.unsplashphoto.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashphoto.MainActivity

import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: UnsplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolBar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_picture_menu, menu)
        searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
    }
}
