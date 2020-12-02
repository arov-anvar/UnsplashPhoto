package com.anvar.unsplashphoto.ui.photo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import kotlinx.android.synthetic.main.photo_fragment.*
import android.os.Bundle
import com.anvar.unsplashphoto.*


class PhotoFragment: Fragment(R.layout.photo_fragment) {

    lateinit var viewModel: UnsplashViewModel
    lateinit var urlPhoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)
        val photoId = arguments?.getString("photoId")
        photoId?.apply {
            viewModel.getPhoto(this).observe(this@PhotoFragment, Observer {photo ->
                urlPhoto = photo.urls.small
                fullPhotoImageView.loadImage(photo.urls.full)
                countLikesTxt.text = "${photo.likes}"
                imageScaleTxt.text = "${photo.width}x${photo.height}px"
                countDownloadsTxt.text = "${photo.downloads}"
                photoDescriptionTxt.text = photo.description
            })
        }

        downloadPhotoBtn.setOnClickListener {
            val str = "Изображение сохранено: " + fullPhotoImageView.toBitmap().saveToStorage(requireContext())
            showToast(requireContext(), str)
        }
    }
}