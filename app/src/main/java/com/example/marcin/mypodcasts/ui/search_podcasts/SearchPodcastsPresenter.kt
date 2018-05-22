package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel.Podcast
import io.reactivex.Observable
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
    private val podcastManager: PodcastManager
) : BasePresenter<SearchPodcastsContract.View>(), SearchPodcastsContract.Presenter {

  override fun resume() {
    super.resume()
    loadPodcasts()
    podcastManager.getAll()
  }

  private fun loadPodcasts() {
    val disposable = getPodcastsUseCase.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe({ podcasts ->
          podcasts.forEach { setPodcastIsSubscribe(it) }
          view.showPodcasts(podcasts)
        }, { error ->
          Timber.d("Error " + error.localizedMessage)
        })
    disposables?.add(disposable)
  }

  override fun handleSubscription(clickedSubscription: Observable<Podcast>) {
    val disposable = clickedSubscription
        .subscribe { podcast ->
          if (!podcast.isSubscribed) {
            podcastManager.put(podcast.id.toString())
          } else {
            podcastManager.delete(podcast.id.toString())
          }
          view.updatePodcastView(setPodcastIsSubscribe(podcast))
        }
    disposables?.add(disposable)
  }

  private fun setPodcastIsSubscribe(podcast: Podcast): Podcast {
    return podcast.apply {
      isSubscribed = podcastManager.get(podcast.id.toString()) ?: false
    }
  }
}