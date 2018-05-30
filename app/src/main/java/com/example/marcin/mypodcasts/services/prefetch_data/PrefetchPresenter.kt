package com.example.marcin.mypodcasts.services.prefetch_data

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2018-05-14.
 */
class PrefetchPresenter @Inject constructor(
    private val prefetchUseCase: PrefetchUseCase
) : PrefetchContract.Presenter {

  private var disposables = CompositeDisposable()

  override fun prefetch() {
    val disposable = prefetchUseCase.prefetch()
        .observeOn(Schedulers.io())
        .doOnComplete { Timber.d("Data prefetched!") }
        .doOnError { e -> Timber.e(e, "Data prefetch failed!") }
        .subscribe()
    disposables.add(disposable)
  }

  override fun destroy() {
    disposables.clear()
  }
}