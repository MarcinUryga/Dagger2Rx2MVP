package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView

/**
 * Created by marci on 2017-10-17.
 */
interface SearchPodcastsContract {

  interface View : MvpView {

    fun showPodcasts(podcastsList: List<Podcast>)

    fun showLoadError(msg: String)
  }

  interface Presenter : MvpPresenter {

    fun loadPodcasts()
  }
}