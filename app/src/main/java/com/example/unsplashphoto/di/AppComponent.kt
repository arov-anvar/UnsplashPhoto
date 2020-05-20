package com.example.unsplashphoto.di

import com.example.unsplashphoto.UnsplashPhotoApp
import com.example.unsplashphoto.model.repository.UnsplashRepository
import com.example.unsplashphoto.ui.UnsplashViewModel
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
}