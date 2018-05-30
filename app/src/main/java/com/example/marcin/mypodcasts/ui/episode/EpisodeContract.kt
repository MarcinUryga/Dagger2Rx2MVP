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

    fun setProgressOnSeekBar(startTime: Int)

    fun incrementSeekBar(progress: Int = 1)

    fun startPlayerService(podcastId: Long, episodeId: Long)

    fun showToast(episode: Episode)
  }

  interface Presenter : MvpPresenter {

    fun handleActionButton(isSelected: Boolean)

    fun handleIncrement()

    fun getEpisode(publishSubject: Observable<Episode>?)
  }
}