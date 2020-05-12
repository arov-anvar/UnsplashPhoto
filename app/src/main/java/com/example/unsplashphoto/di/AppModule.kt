package com.example.unsplashphoto.di

import android.app.Application
import com.example.unsplashphoto.data.NetworkApiService
import com.example.unsplashphoto.data.repository.UnsplashRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideUnsplashRepository() = UnsplashRepositoryImpl(apiService = NetworkApiService.invoke())

}