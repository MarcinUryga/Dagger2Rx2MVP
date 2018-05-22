package com.example.marcin.mypodcasts.ui.podcast_details

import android.os.Bundle

/**
 * Created by marci on 2018-05-14.
 */
class PodcastIdParam(bundle: Bundle? = Bundle()) {

  val data: Bundle = bundle ?: Bundle()

  var podcastId: Long
    get() = data.getLong(PODCAST_ID)
    set(value) = data.putLong(PODCAST_ID, value)

  constructor(id: Long) : this() {
    this.podcastId = id
  }

  companion object {
    const val PODCAST_ID = "podcastId"
  }
}