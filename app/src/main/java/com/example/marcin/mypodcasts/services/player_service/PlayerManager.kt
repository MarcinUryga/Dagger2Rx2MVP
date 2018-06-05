package com.example.marcin.mypodcasts.services.player_service

import android.media.MediaPlayer
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit
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
  private var currentAudioUrl: String = ""


  fun preparePlayer(audioUrl: String) {
    reset()
    currentAudioUrl = audioUrl
    setDataSource(currentAudioUrl)
    prepare()
  }

  fun onPlay(): Observable<Pair<Int, Int>>? {
    if (!isStarted) {
      start()
      isStarted = true
      return ticks()
    }
    return null
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

  fun getCurrentAudioUrl() = currentAudioUrl

  fun ticks(): Observable<Pair<Int, Int>> {
    return Observable.interval(16, TimeUnit.MILLISECONDS)
        .map { _ ->
          val currentPositionInSeconds = currentPosition / 1000
          val durationInSeconds = duration / 1000
          return@map Pair(currentPositionInSeconds, durationInSeconds)
        }
  }

  fun killMediaPlayer() {
    try {
      release()
    } catch (e: Exception) {
      Timber.e(e.localizedMessage)
    }
  }

  companion object {

    const val CLOSE_PLAYER = "close_player"
    const val TICKS = "ticks"
  }
}