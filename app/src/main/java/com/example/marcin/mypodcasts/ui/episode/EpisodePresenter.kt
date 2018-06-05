package com.example.marcin.mypodcasts.ui.episode

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by marci on 2018-05-18.
 */
@ScreenScope
class EpisodePresenter @Inject constructor(
    private val podcastIdParam: PodcastIdParam,
    private val episodeIdParams: EpisodeIdParams,
    private val getEpisodeUseCase: GetEpisodeUseCase
) : BasePresenter<EpisodeContract.View>(), EpisodeContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getEpisodeUseCase.get(podcastIdParam.podcastId, episodeIdParams.episodeId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe { episode ->
          view.showEpisodeDetails(episode)
        }
    disposables?.addAll(disposable)
  }

  override fun getEpisode(publishSubject: Observable<Episode>?) {
    val disposable = publishSubject
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
        ?.subscribe { episode ->
          view.showEpisodeDetails(episode)
        }
    disposables?.addAll(disposable)
  }

  override fun getTicks(ticksPublishSubject: Observable<Pair<Int, Int>>?) {
    val disposable = ticksPublishSubject
        ?.subscribe { ticks ->
          view.updateTimer(ticks)
        }
    disposables?.addAll(disposable)
  }
}