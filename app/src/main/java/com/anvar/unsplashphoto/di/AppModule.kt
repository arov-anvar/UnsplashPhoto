package com.anvar.unsplashphoto.di

import com.anvar.unsplashphoto.model.UnsplashApiService
import com.anvar.unsplashphoto.model.repository.UnsplashRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideUnsplashRepository() = UnsplashRepositoryImpl(apiService = UnsplashApiService.invoke())

}