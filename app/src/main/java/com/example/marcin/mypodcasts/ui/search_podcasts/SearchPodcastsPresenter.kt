package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.storage.DataManager
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2017-10-17.
 */

@ScreenScope
class SearchPodcastsPresenter @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val bus: Bus,
    private val dataManager: DataManager
) : BasePresenter<SearchPodcastsContract.View>(), SearchPodcastsContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    bus.register(this)
    dataManager.createPodcast(Podcast(
        podcastId = 1L,
        numberOfEpisodes = 4,
        title = "title",
        description = "description",
        fullUrl = "fullUrl",
        thumbUrl = "thumbUrl"
    ))
    val podcast = dataManager.getPodcast(1L)

    Timber.d(podcast.toString())
  }

  override fun loadPodcasts() {
    val disposable = getPodcastsUseCase.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe({ response ->
          view.showPodcasts(response.results, bus)
        }, { error ->
          Timber.d("Error " + error.localizedMessage)
        })
    disposables?.add(disposable)
  }

  @Subscribe
  public fun onPodcastDetailsEvent(event: PodcastDetailsEvent) {
    Timber.d("${event.podcast.podcastId} dffssdfsdf")
  }

  private fun handleResponse(androidList: List<Podcast>) {

    Timber.d("${androidList.size}")
  }

}