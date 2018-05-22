package com.example.marcin.mypodcasts.ui.episode

import android.media.MediaPlayer
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2018-05-19.
 */
class PlayerManager @Inject constructor(
) : MediaPlayer() {

  private var isStarted = false
  private val startTime = 0.0
  private var endTime = 0
  private val forwardTime = 5
  private val backwardTime = 5

  fun preparePlayer(audioUrl: String) {
    reset()
    setDataSource(audioUrl)
    prepare()
  }

  fun onPlay() {
    if (!isStarted) {
      start()
      isStarted = true
    }
  }

  fun onPause() {
    if (isStarted) {
      pause()
      isStarted = false
    }
  }

  fun onStop() {
    if (isStarted) {
      stop()
      isStarted = false
    }
  }

  fun killMediaPlayer() {
    try {
      release()
    } catch (e: Exception) {
      Timber.e(e.localizedMessage)
    }
  }
}