package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.PodcastApplication
import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.prefs.PrefsManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by MARCIN on 2017-10-14.
 */

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providePrefsManager(app: PodcastApplication): PrefsManager = PrefsManager(app)

    companion object {
        private val baseUrl = "https://parseapi.back4app.com/"
    }
/*
    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request.newBuilder()
                            .addHeader("X-Parse-Application-Id", "DH07L5rkIz4noOLJ6I22pfFlKkh16XI2t2LURZp3")
                            .addHeader("X-Parse-REST-API-Key", "b4OB3vVj5eThfT66OAMt76hfQMVvDN08oV2HedyR")
                            .addHeader("X-Parse-Revocable-Session", "1").build()
                    chain.proceed(newRequest)
                }
                .addNetworkInterceptor(loggingInterceptor).build()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    } */

    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request.newBuilder()
                            .addHeader("X-Parse-Application-Id", "DH07L5rkIz4noOLJ6I22pfFlKkh16XI2t2LURZp3")
                            .addHeader("X-Parse-REST-API-Key", "b4OB3vVj5eThfT66OAMt76hfQMVvDN08oV2HedyR")
                            .addHeader("X-Parse-Revocable-Session", "1").build()
                    chain.proceed(newRequest)
                }
                .addNetworkInterceptor(loggingInterceptor).build()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    fun providePodcastApi(retrofit: Retrofit) = retrofit.create(PodcastApi::class.java)
}

