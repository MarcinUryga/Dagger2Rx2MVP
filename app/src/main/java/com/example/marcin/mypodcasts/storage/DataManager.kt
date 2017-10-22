package com.example.marcin.mypodcasts.storage

import android.content.Context
import android.content.res.Resources
import com.example.marcin.mypodcasts.di.ApplicationContext
import com.example.marcin.mypodcasts.model.Podcast
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by marci on 2017-10-21.
 */

@Singleton
class DataManager @Inject constructor(
    @ApplicationContext context: Context,
    private val dbHelper: DbHelper) {

  @Throws(Exception::class)
  fun createPodcast(podcast: Podcast): Long? {
    return dbHelper.insertPodcast(podcast)
  }

  @Throws(Resources.NotFoundException::class, NullPointerException::class)
  fun getPodcast(podcastId: Long): Podcast {
    return dbHelper.getPodcast(podcastId)
  }
}