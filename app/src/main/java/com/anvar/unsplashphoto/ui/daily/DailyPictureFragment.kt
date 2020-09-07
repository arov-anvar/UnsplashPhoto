package com.anvar.unsplashphoto.ui.daily

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import com.anvar.unsplashphoto.loadImage
import kotlinx.android.synthetic.main.daily_picture_fragment.*

class DailyPictureFragment : Fragment() {

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

        authorImageView.setOnClickListener {
            val args = bundleOf("userName" to authorFullNameTextView.text)
            it.findNavController().navigate(R.id.userFragment, args)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadPictureDay() {
        viewModel.getPictureDay().observe(viewLifecycleOwner, Observer {dailyPicture ->
            progressBar.visibility = View.GONE
            mainContentConstraintLayout.visibility = View.VISIBLE
            dailyPictureImageView.loadImage(dailyPicture.urls.regular)
            authorImageView.loadImage(dailyPicture.user.profileImage.large)
            countLikesTextView.text = dailyPicture.likes.toString()
            permissionPictureTextView.text = "${dailyPicture.height}x${dailyPicture.width}px"
            authorFullNameTextView.text = dailyPicture.user.username
            authorInstagramNameTextView.text = "@${dailyPicture.user.instagramUsername}"

            dailyPictureImageView.setOnClickListener {
                val args = bundleOf("photoId" to dailyPicture.id)
                it.findNavController().navigate(R.id.photoFragment, args)
            }
        })
    }

}
