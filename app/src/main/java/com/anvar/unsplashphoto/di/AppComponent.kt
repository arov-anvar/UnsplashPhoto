package com.anvar.unsplashphoto.di

import com.anvar.unsplashphoto.UnsplashPhotoApp
import com.anvar.unsplashphoto.model.repository.UnsplashRepository
import com.anvar.unsplashphoto.ui.UnsplashViewModel
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