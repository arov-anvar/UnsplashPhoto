package com.example.unsplashphoto.ui.daily

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.unsplashphoto.R

class DailyPictureFragment : Fragment() {

    companion object {
        fun newInstance() =
            DailyPictureFragment()
    }

    private lateinit var viewModel: DailyPictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_picture_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DailyPictureViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
