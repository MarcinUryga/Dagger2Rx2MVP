package com.example.marcin.mypodcasts.ui.my_podcasts

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by marci on 2018-05-11.
 */
@ScreenScope
class MyPodcastsPresenter @Inject constructor(
    private val getSubscribedPodcastsUseCase: GetSubscribedPodcastsUseCase
) : BasePresenter<MyPodcastsContract.View>(), MyPodcastsContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = getSubscribedPodcastsUseCase.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe { podcasts ->
          if (podcasts.isEmpty()) {
            view.showEmptyView()
          }
          view.showSubscribedPodcasts(podcasts)
        }
    disposables?.addAll(disposable)
  }

  override fun handleClickedPodcast(clickedPublishSubject: Observable<Long>) {
    val disposable = clickedPublishSubject
        .subscribe { id ->
          view.startPodcastDetails(id)
        }
    disposables?.addAll(disposable)
  }
}