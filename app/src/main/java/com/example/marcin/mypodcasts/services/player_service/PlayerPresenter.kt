package com.example.marcin.mypodcasts.services.player_service

import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by marci on 2018-05-30.
 */
class PlayerPresenter @Inject constructor(
    private val playerManager: PlayerManager
) : PlayerContract.Presenter {

  @Inject lateinit var service: PlayerContract.Service
  private val disposables = CompositeDisposable()
  private val ticksPublishSubject = PublishSubject.create<Pair<Int, Int>>()
  private var isPlayed = false
  private var playerState = PlayerState.LOADING
  private var timerDisposable: Disposable? = null

  override fun setPlayerState(state: PlayerState) {
    playerState = state
  }

  override fun preparePlayerManager(audioUrl: String) {
    if (playerManager.getCurrentAudioUrl() != audioUrl) {
      if (playerState == PlayerState.PLAY) {
        pause()
      }
      playerManager.preparePlayer(audioUrl)
      playerState = PlayerState.PAUSE
    }
  }

  override fun handlePlayOrPause(episode: Episode): PlayerState {
    return when (playerState) {
      PlayerState.LOADING -> {
        preparePlayerManager(episode.audioUrl)
        play(episode)
      }
      PlayerState.PAUSE -> play(episode)
      else -> {
        pause()
      }
    }
  }

  private fun pause(): PlayerState {
    playerState = PlayerState.PAUSE
    timerDisposable?.dispose()
    playerManager.onPause()
    return playerState
  }

  private fun play(episode: Episode): PlayerState {
    service.createNotification(episode)
    playerState = PlayerState.PLAY
    timerDisposable = playerManager.onPlay()
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
        ?.subscribe { ticks ->
          ticksPublishSubject.onNext(ticks)
        }
    return playerState
  }

  override fun getTicksPublishSubject() = ticksPublishSubject

  override fun getPlayerState() = playerState

  override fun destroy() {
    timerDisposable?.dispose()
    playerManager.killMediaPlayer()
  }
}