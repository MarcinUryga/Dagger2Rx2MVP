package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView
import com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel.Podcast
import com.squareup.otto.Bus
import io.reactivex.Observable

/**
 * Created by marci on 2017-10-17.
 */
interface SearchPodcastsContract {

  interface View : MvpView {

    fun showPodcasts(podcastsList: List<Podcast>)

    fun showLoadError(msg: String)

    fun showProgressBar()

    fun hideProgressBar()

    fun updatePodcastView(podcast: Podcast)
  }

  interface Presenter : MvpPresenter {

    fun handleSubscription(clickedSubscription: Observable<Podcast>)
  }
}