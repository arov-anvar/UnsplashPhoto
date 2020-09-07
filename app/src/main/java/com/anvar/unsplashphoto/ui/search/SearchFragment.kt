package com.anvar.unsplashphoto.ui.search

import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anvar.unsplashphoto.ui.MainActivity
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import com.anvar.unsplashphoto.ui.search.adapter.SearchAdapter
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment() {

    private lateinit var viewModel: UnsplashViewModel
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private val photoAdapter = SearchAdapter()
    private var typeSearch: Int = 1

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

        searchPictureImg.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_picture_menu, menu)
        searchView = menu.findItem(R.id.search_action).actionView as androidx.appcompat.widget.SearchView
        searchView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressBarSearch.visibility = View.VISIBLE
                Toast.makeText(context, "Query: $query", Toast.LENGTH_SHORT).show()
                viewModel.search(typeSearch, query.toString(), 1).observe(this@SearchFragment, Observer {
                    progressBarSearch.visibility = View.GONE
                    if (it.isNotEmpty()) {
                        photoAdapter.setData(it)
                        showResultSearch(View.VISIBLE, View.GONE)
                    } else {
                        showResultSearch(View.GONE, View.VISIBLE)
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

    private fun showResultSearch(recyclerView: Int, searchPictureView: Int) {
        resultsRecycler.visibility = recyclerView
        searchPictureImg.visibility = searchPictureView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)

        selectTypeSearch.setOnCheckedChangeListener { group, checkedId ->
            typeSearch = checkedId
            for (index in 1..group.childCount) {
                changeStateButton(group.getChildAt(index - 1) as RadioButton)
            }
        }
    }

    private fun changeStateButton(btn: RadioButton) {
        if (!btn.isChecked) {
            DrawableCompat.setTint(btn.compoundDrawables[1], ContextCompat.getColor(requireContext(), R.color.colorWhite))
            btn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        } else {
            DrawableCompat.setTint(btn.compoundDrawables[1], ContextCompat.getColor(requireContext(), R.color.colorRed))
            btn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        }
    }


}
