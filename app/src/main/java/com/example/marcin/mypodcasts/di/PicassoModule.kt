package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.PodcastApplication
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

/**
 * Created by marci on 2018-05-11.
 */
@Module
class PicassoModule {

  companion object {
    private const val CACHE_SIZE = 30L * 1024 * 1024 //30MB
  }

  @Provides
  fun providesPicasso(podcastApplication: PodcastApplication): Picasso {
    return Picasso.Builder(podcastApplication)
        .downloader(OkHttp3Downloader(podcastApplication, CACHE_SIZE))
        .loggingEnabled(true)
        .build()
  }
}