package com.example.marcin.mypodcasts.ui.episode

import android.os.Bundle

/**
 * Created by marci on 2018-05-18.
 */
class EpisodeIdParams(bundle: Bundle? = Bundle()) {

  val data: Bundle = bundle ?: Bundle()

  var episodeId: Long
    get() = data.getLong(EPISODE_ID)
    set(value) = data.putLong(EPISODE_ID, value)

  constructor(id: Long) : this() {
    this.episodeId = id
  }

  companion object {
    const val EPISODE_ID = "episodeId"
  }
}
