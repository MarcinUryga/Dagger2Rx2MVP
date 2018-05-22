package com.example.marcin.mypodcasts.sharedprefs

import android.content.Context
import com.example.marcin.mypodcasts.PodcastApplication
import javax.inject.Inject

/**
 * Created by marci on 2018-05-12.
 */
class PrefsManager @Inject constructor(
    private val podcastApplication: PodcastApplication
) {

  fun getPreferencesStorage(prefsFileName: String = DEFAULT_PREFS_FILE): PrefsStorage {
    return PrefsStorage(podcastApplication.getSharedPreferences(prefsFileName, Context.MODE_PRIVATE))
  }

  companion object {
    const val DEFAULT_PREFS_FILE: String = "default_prefs"
    const val PODCASTS = "podcasts"
    const val USER = "user"
  }
}