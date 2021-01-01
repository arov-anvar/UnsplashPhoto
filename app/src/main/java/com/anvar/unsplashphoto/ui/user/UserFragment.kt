package com.anvar.unsplashphoto.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.anvar.unsplashphoto.R
import com.anvar.unsplashphoto.loadImage
import com.anvar.unsplashphoto.model.entity.user_images.UserImagesItem
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment: Fragment(R.layout.user_fragment) {

    lateinit var viewModel: UnsplashViewModel

    val list = mutableListOf<UserImageItem>()
    val adapter = UserImagesAdapter(list)
    var pageNumber = 1
    var countImages = Int.MAX_VALUE
    private val perPage = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)

        val userName = arguments?.getString("userName") ?: ""
        imagesRecyclerView.adapter = adapter

        val observer = Observer<List<UserImageItem>> { userImages ->
            if (pageNumber == 1) list.clear()
            list.addAll(userImages)
            adapter.notifyDataSetChanged()
            ++pageNumber
        }

        viewModel.getUser(userName).observe(this@UserFragment, Observer {user ->
            userNameTxt.text = user.username
            pictureUserImg.loadImage(user.profileImage.large)
            followersTxt.setCount(user.followersCount)
            followingTxt.setCount(user.followingCount)
            downloadsTxt.setCount(user.downloads)
            totalLikesTxt.setCount(user.totalLikes)
            totalPhotosTxt.setCount(user.totalPhotos)
            totalCollectionsTxt.setCount(user.totalCollections)

            countImages = user.totalPhotos
        })

        viewModel.userImagesLiveData.observe(this@UserFragment, observer)
        viewModel.getUserImages(userName, pageNumber, perPage)

        imagesRecyclerView.addOnScrollListener(object : RecyclerViewPaginator(imagesRecyclerView) {
            override fun loadMore() {
                viewModel.getUserImages(userName, pageNumber, perPage)
            }
        })

    }
}