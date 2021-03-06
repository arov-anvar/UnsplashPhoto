package com.anvar.unsplashphoto.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.loadImage
import com.anvar.unsplashphoto.model.entity.user_images.UserImagesItem
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment: Fragment(R.layout.user_fragment) {

    lateinit var viewModel: UserViewModel

    val list = mutableListOf<UserImageItem>()
    val adapter = UserImagesAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userName = arguments?.getString("userName") ?: ""
        list.clear()
        adapter.notifyDataSetChanged()
        imagesRecyclerView.adapter = adapter

        viewModel.getUser(userName).observe(this@UserFragment, Observer {user ->
            userNameTxt.text = user.username
            pictureUserImg.loadImage(user.profileImage.large)
            followersTxt.setCount(user.followersCount)
            followingTxt.setCount(user.followingCount)
            downloadsTxt.setCount(user.downloads)
            totalLikesTxt.setCount(user.totalLikes)
            totalPhotosTxt.setCount(user.totalPhotos)
            totalCollectionsTxt.setCount(user.totalCollections)
        })

        viewModel.userImagesLiveData.observe(this@UserFragment) { userImages ->
            viewModel.isLoading = false
            list.addAll(userImages)
            adapter.notifyDataSetChanged()
        }

        imagesRecyclerView.addOnScrollListener(object : RecyclerViewPaginator(imagesRecyclerView) {
            override fun loadMore() {
                viewModel.nextPage()
            }
        })

    }
}