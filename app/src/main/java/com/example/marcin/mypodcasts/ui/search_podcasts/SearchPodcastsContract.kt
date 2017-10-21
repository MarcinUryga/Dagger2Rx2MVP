package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView
import com.squareup.otto.Bus

/**
 * Created by marci on 2017-10-17.
 */
interface SearchPodcastsContract {

  interface View : MvpView {

    fun showPodcasts(podcastsList: List<Podcast>, bus: Bus)

    fun showLoadError(msg: String)

    fun showProgressBar()

    fun hideProgressBar()

    fun startPodcastDetailsActivity()
  }

  interface Presenter : MvpPresenter {

    fun loadPodcasts()
  }
}