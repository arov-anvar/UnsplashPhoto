package com.example.unsplashphoto.ui.daily

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.dialogs.InfoBottomSheet
import com.example.unsplashphoto.ui.loadImage
import kotlinx.android.synthetic.main.daily_picture_fragment.*

class DailyPictureFragment : Fragment() {

    companion object {
        fun newInstance() =
            DailyPictureFragment()
    }

    private lateinit var viewModel: UnsplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_picture_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)
        loadPictureDay()
    }

    @SuppressLint("SetTextI18n")
    private fun loadPictureDay() {
        viewModel.getPictureDay().observe(viewLifecycleOwner, Observer {
            dailyPictureImageView.loadImage(it.urls.regular)
            authorImageView.loadImage(it.user.profileImage.large)
            countLikesTextView.text = it.likes.toString()
            permissionPictureTextView.text = "${it.height}x${it.width}px"
            authorFullNameTextView.text = it.user.firstName
            authorInstagramNameTextView.text = "@${it.user.instagramUsername}"
        })
    }

}
