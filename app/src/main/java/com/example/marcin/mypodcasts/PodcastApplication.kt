package com.example.marcin.mypodcasts

import com.example.marcin.mypodcasts.di.DaggerApplicationComponent
import com.example.marcin.mypodcasts.repository.RealmManager
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by MARCIN on 2017-10-14.
 */
class PodcastApplication : DaggerApplication() {

  @Inject lateinit var picasso: Picasso

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
    RealmManager.init(baseContext)
    Picasso.setSingletonInstance(picasso)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerApplicationComponent.builder().create(this)
  }
}