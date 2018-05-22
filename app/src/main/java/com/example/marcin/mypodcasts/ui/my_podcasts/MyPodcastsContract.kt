package com.example.marcin.mypodcasts.ui.my_podcasts

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView
import com.example.marcin.mypodcasts.ui.my_podcasts.viewmodel.Podcast
import io.reactivex.Observable

/**
 * Created by marci on 2018-05-11.
 */
interface MyPodcastsContract {

  interface View : MvpView {

    fun showProgressBar()

    fun hideProgressBar()

    fun showSubscribedPodcasts(podcasts: List<Podcast>)

    fun showEmptyView()

    fun startPodcastDetails(id: Long)
  }

  interface Presenter : MvpPresenter {

    fun handleClickedPodcast(clickedPublishSubject: Observable<Long>)
  }
}