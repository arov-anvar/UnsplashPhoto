package com.anvar.unsplashphoto.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.loadImage
import com.anvar.unsplashphoto.model.entity.user_images.UserImagesItem
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment: Fragment() {

    lateinit var viewModel: UnsplashViewModel

    val list = mutableListOf<UserImageItem>()
    val adapter = UserImagesAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)

        val userName = arguments?.getString("userName")
        imagesRecyclerView.adapter = adapter

        userName?.apply {
            viewModel.getUser(this).observe(this@UserFragment, Observer {user ->
                userNameTxt.text = user.username
                pictureUserImg.loadImage(user.profileImage.large)
                followersTxt.setCount(user.followersCount)
                followingTxt.setCount(user.followingCount)
                downloadsTxt.setCount(user.downloads)
                totalLikesTxt.setCount(user.totalLikes)
                totalPhotosTxt.setCount(user.totalPhotos)
                totalCollectionsTxt.setCount(user.totalCollections)
            })

            viewModel.getUserImages(this).observe(this@UserFragment, Observer { userImages ->
                list.clear()
                list.addAll(userImages)
                adapter.notifyDataSetChanged()
            })
        }

    }
}