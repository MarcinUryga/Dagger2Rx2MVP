package com.example.marcin.mypodcasts.ui.podcast_details

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.Episode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2018-05-14.
 */
@ScreenScope
class PodcastDetailsPresenter @Inject constructor(
    private val podcastIdParam: PodcastIdParam,
    private val getPodcastDetailsUseCase: GetPodcastDetailsUseCase
) : BasePresenter<PodcastDetailsContract.View>(), PodcastDetailsContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
      val disposable = getPodcastDetailsUseCase.get(podcastIdParam.podcastId)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe { view.showProgressBar() }
          .doFinally { view.hideProgressBar() }
          .subscribe { podcastDetails ->
            view.showDetails(podcastDetails)
          }
      disposables?.addAll(disposable)
  }

  override fun handleClickedEpisode(onClickedEpisode: Observable<Episode>) {
    val disposable = onClickedEpisode.subscribe { episode ->
      view.startEpisodeActivity(episode.podcastId, episode.episodeId, episode.audioUrl)
    }
    disposables?.addAll(disposable)
  }
}