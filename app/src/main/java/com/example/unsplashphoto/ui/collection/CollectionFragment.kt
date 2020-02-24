package com.example.unsplashphoto.ui.collection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.collection.adapter.CollectionAdapter
import com.example.unsplashphoto.ui.collection.adapter.CollectionItem
import kotlinx.android.synthetic.main.collection_fragment.*

class CollectionFragment : Fragment() {

    private val collectionAdapter = CollectionAdapter()
    private lateinit var viewModel: CollectionViewModel

    companion object {
        fun newInstance() = CollectionFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.collection_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CollectionViewModel::class.java)
        // TODO: Use the ViewModel

        with(collectionRecyclerView) {
            adapter = collectionAdapter
            layoutManager = GridLayoutManager(context, 3)
        }

        val collection = mutableListOf<CollectionItem>()
        collection.add(CollectionItem(R.drawable.space))
        collection.add(CollectionItem(R.drawable.space))
        collection.add(CollectionItem(R.drawable.space))
        collection.add(CollectionItem(R.drawable.space))
        collection.add(CollectionItem(R.drawable.space))
        collection.add(CollectionItem(R.drawable.space))

        collectionAdapter.setPitures(collection)
    }

}
