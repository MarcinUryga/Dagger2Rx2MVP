package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2017-10-17.
 */

@ScreenScope
class SearchPodcastsPresenter @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase
) : BasePresenter<SearchPodcastsContract.View>(), SearchPodcastsContract.Presenter {

  override fun loadPodcasts() {
    val disposable = getPodcastsUseCase.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response ->
          view.showPodcasts(response.results)
        }, { error ->
          Timber.d("Error " + error.localizedMessage)
        })
    disposables?.add(disposable)
  }

  private fun handleResponse(androidList: List<Podcast>) {

    Timber.d("${androidList.size}")
  }

}