package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.PodcastApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by MARCIN on 2017-10-14.
 */

@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    InjectorsModule::class,
    ApplicationModule::class,
    PicassoModule::class
))
interface ApplicationComponent : AndroidInjector<PodcastApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<PodcastApplication>()
}