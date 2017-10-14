package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.PodcastApplication
import com.example.marcin.mypodcasts.prefs.PrefsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MARCIN on 2017-10-14.
 */

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providePrefsManager(app: PodcastApplication): PrefsManager = PrefsManager(app)
}

