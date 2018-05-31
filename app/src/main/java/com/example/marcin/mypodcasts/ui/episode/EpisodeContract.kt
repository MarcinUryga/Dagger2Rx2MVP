package com.example.marcin.mypodcasts.ui.episode

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import io.reactivex.Observable

/**
 * Created by marci on 2018-05-18.
 */
interface EpisodeContract {

  interface View : MvpView {

    fun showEpisodeDetails(episode: Episode)

    fun showProgressBar()

    fun hideProgressBar()

    fun updatePlayButtonState(isSelected: Boolean)

    fun startPlayerService(podcastId: Long, episodeId: Long)

    fun updateTimer(ticks: Pair<Int, Int>)
  }

  interface Presenter : MvpPresenter {

    fun getEpisode(publishSubject: Observable<Episode>?)

    fun getTicks(ticksPublishSubject: Observable<Pair<Int, Int>>?)
  }
}