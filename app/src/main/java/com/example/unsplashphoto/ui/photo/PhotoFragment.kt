package com.example.unsplashphoto.ui.photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashphoto.R
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.loadImage
import kotlinx.android.synthetic.main.photo_fragment.*

class PhotoFragment: Fragment() {

    lateinit var viewModel: UnsplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)
        val photoId = arguments!!.getString("photoId")

        if (photoId != null) {
            viewModel.getPhoto(photoId).observe(this@PhotoFragment, Observer {photo ->
                fullPhotoImageView.loadImage(photo.urls.full)
                countLikesTxt.text = "${photo.likes}"
                imageScaleTxt.text = "${photo.width}x${photo.height}px"
                countDownloadsTxt.text = "${photo.downloads}"
                photoDescriptionTxt.text = photo.description
            })
        }

    }
}