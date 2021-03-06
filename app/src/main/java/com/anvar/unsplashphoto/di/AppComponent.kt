package com.anvar.unsplashphoto.di

import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.model.repository.UnsplashRepository
import com.anvar.unsplashphoto.ui.UnsplashViewModel
import com.anvar.unsplashphoto.ui.collection.CurrentCollectionViewModel
import com.anvar.unsplashphoto.ui.user.UserViewModel
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
    fun inject(repository: UnsplashRepository)
    fun inject(viewModel: UnsplashViewModel)
    fun inject(viewModel: UserViewModel)
    fun inject(viewModel: CurrentCollectionViewModel)
}