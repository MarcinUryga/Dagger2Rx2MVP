package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.ui.login.LoginActivity
import com.example.marcin.mypodcasts.ui.login.LoginModule
import com.example.marcin.mypodcasts.ui.main.MainActivity
import com.example.marcin.mypodcasts.ui.main.MainModule
import com.example.marcin.mypodcasts.ui.register.RegisterActivity
import com.example.marcin.mypodcasts.ui.register.RegisterModule
import com.example.marcin.mypodcasts.ui.search_podcasts.SearchPodcastsFragment
import com.example.marcin.mypodcasts.ui.search_podcasts.SearchPodcastsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by MARCIN on 2017-10-14.
 */

@Module
abstract class InjectorsModule {

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
  abstract fun loginActivity(): LoginActivity

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(RegisterModule::class))
  abstract fun registerActivity(): RegisterActivity

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
  abstract fun mainActivity(): MainActivity

  @ScreenScope
  @ContributesAndroidInjector(modules = arrayOf(SearchPodcastsModule::class))
  abstract fun searchPodcastsFragment(): SearchPodcastsFragment
}