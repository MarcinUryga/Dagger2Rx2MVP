package com.example.marcin.mypodcasts.ui.podcast_details

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.PodcastDetails
import io.reactivex.Observable

/**
 * Created by marci on 2018-05-14.
 */
interface PodcastDetailsContract {

  interface View : MvpView {

    fun showProgressBar()

    fun hideProgressBar()

    fun showDetails(podcastDetails: PodcastDetails)

    fun startEpisodeActivity(podcastId: Long, episodeId: Long)
  }

  interface Presenter : MvpPresenter {

    fun handleClickedEpisode(onClickedEpisode: Observable<Episode>)
  }
}