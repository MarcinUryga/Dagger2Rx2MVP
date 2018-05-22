package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.sharedprefs.PrefsManager
import javax.inject.Inject

/**
 * Created by marci on 2018-05-12.
 */
class PodcastManager @Inject constructor(
    prefsManager: PrefsManager
) {

  val sharedPrefs = prefsManager.getPreferencesStorage(PrefsManager.PODCASTS)

  fun put(podcastId: String) = sharedPrefs.put(podcastId, true)

  fun get(podcastId: String) = sharedPrefs.get<Boolean>(podcastId)

  fun delete(podcastId: String) = sharedPrefs.put(podcastId, null)

  fun getAll() = sharedPrefs.getAll()
}