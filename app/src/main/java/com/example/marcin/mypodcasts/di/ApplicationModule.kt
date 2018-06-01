package com.example.marcin.mypodcasts.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.marcin.mypodcasts.PodcastApplication
import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.repository.RealmManager
import com.squareup.otto.Bus
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

  companion object {
    private val baseUrl = "https://parseapi.back4app.com/"
  }

  @Provides
  @ApplicationContext
  fun provideContext(podcastApplication: PodcastApplication): Context {
    return podcastApplication
  }

  @Provides
  fun provideApplication(podcastApplication: PodcastApplication): Application {
    return podcastApplication
  }

  @Provides
  @DatabaseInfo
  fun provideDatabaseName(): String {
    return "podcast-database.db"
  }

  @Provides
  @DatabaseInfo
  fun provideDatabaseVersion(): Int {
    return 2
  }

  @Provides
  fun providesSharedPreferences(app: PodcastApplication): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(app.applicationContext)
  }

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
              .addHeader("limit", "1000")
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

  @Provides
  fun providesRealmManager() = RealmManager

  @Singleton
  @Provides
  fun provideBus() = Bus()
}

