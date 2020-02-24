package com.example.unsplashphoto.data

import com.example.unsplashphoto.BuildConfig
import com.example.unsplashphoto.data.collections.CollectionResponse
import com.example.unsplashphoto.data.photos.PhotosResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApiService {

    companion object {
        operator fun invoke(): NetworkApiService {
            val requestInterceptor = Interceptor {chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("client_id", BuildConfig.ACCESS_KEY)
                    .build()

                val req = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(req)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApiService::class.java)
        }
    }

    @GET("/collections?per_page=30&page=10")
    fun getCollectionsAsync(): Deferred<List<CollectionResponse>>

    @GET("/collections/{id}/photos")
    fun getCollectionPhotosAsyns(@Path("id") id: Int): Deferred<List<PhotosResponse>>
}