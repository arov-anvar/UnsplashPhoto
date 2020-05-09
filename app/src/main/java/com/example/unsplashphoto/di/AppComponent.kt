package com.example.unsplashphoto.di

import com.example.unsplashphoto.UnsplashPhotoApp
import com.example.unsplashphoto.data.repository.UnsplashRepository
import com.example.unsplashphoto.ui.UnsplashViewModel
import com.example.unsplashphoto.ui.gallery.GalleryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(unsplashPhotoApp: UnsplashPhotoApp)
    fun inject(galleryFragment: GalleryFragment)
    fun inject(repository: UnsplashRepository)
    fun inject(viewModel: UnsplashViewModel)
}