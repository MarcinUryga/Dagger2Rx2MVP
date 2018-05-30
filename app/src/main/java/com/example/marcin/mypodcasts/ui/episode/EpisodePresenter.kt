package com.example.marcin.mypodcasts.ui.episode

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by marci on 2018-05-18.
 */
@ScreenScope
class EpisodePresenter @Inject constructor(
    private val podcastIdParam: PodcastIdParam,
    private val episodeIdParams: EpisodeIdParams,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val playerManager: PlayerManager
) : BasePresenter<EpisodeContract.View>(), EpisodeContract.Presenter {

  override fun start() {
    super.start()
    view.startPlayerService(podcastIdParam.podcastId, episodeIdParams.episodeId)
  }

  override fun getEpisode(publishSubject: Observable<Episode>?) {
    val disposable = publishSubject?.subscribe { episode ->
      view.showEpisodeDetails(episode)
    }
    disposables?.addAll(disposable)
  }

  /*override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getEpisodeUseCase.get(podcastIdParam.podcastId, episodeIdParams.episodeId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe { episode ->
          playerManager.preparePlayer(episode.audioUrl)
        }
    disposables?.addAll(disposable)
  }*/

  override fun handleIncrement() {
    val disposable = Single.timer(1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe { _ ->
          view.incrementSeekBar()
        }
    disposables?.addAll(disposable)
  }

  override fun handleActionButton(isSelected: Boolean) {
    if (isSelected) {
      playerManager.onPause()
    } else {
      handleIncrement()
      playerManager.onPlay()
    }
    view.updatePlayButtonState(!isSelected)
  }

  override fun destroy() {
    super.destroy()
    playerManager.killMediaPlayer()
  }
}