package com.example.marcin.mypodcasts.services.player_service

import com.example.marcin.mypodcasts.ui.episode.GetEpisodeUseCase
import com.example.marcin.mypodcasts.ui.episode.PlayerManager
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by marci on 2018-05-30.
 */
class PlayerPresenter @Inject constructor(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val playerManager: PlayerManager
) : PlayerContract.Presenter {

  @Inject lateinit var service: PlayerContract.Service
  private val disposables = CompositeDisposable()
  private val publishSubject = PublishSubject.create<Episode>()
  private var isPlayed = false

  override fun getEpisode(podcastId: Long, episodeId: Long) {
    val disposable = getEpisodeUseCase.get(podcastId, episodeId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { episode ->
          if (!isPlayed) {
            playerManager.preparePlayer(episode.audioUrl)
            service.createNotification(episode)
          }
          publishSubject.onNext(episode)
        }
    disposables.addAll(disposable)
  }

  override fun handlePlayOrPause(): Boolean {
    isPlayed = if (!isPlayed) {
      playerManager.onPlay()
      true
    } else {
      playerManager.onPause()
      false
    }
    return isPlayed
  }

  override fun getPublishSubject() = publishSubject

  override fun getIsPlayedState() = isPlayed
}