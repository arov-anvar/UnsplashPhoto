package com.example.unsplashphoto.di

import com.example.unsplashphoto.AppDelegate
import com.example.unsplashphoto.ui.collection.CollectionViewModel
import com.example.unsplashphoto.ui.gallery.GalleryFragment
import com.example.unsplashphoto.ui.gallery.GalleryViewModel
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
    fun inject(appDelegate: AppDelegate)
    fun inject(galleryFragment: GalleryFragment)
    fun inject(galleryViewModel: GalleryViewModel)
    fun inject(collectionViewModel: CollectionViewModel)
}