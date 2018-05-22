package com.example.marcin.mypodcasts.ui.launch

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by marci on 2018-05-11.
 */
@ScreenScope
class LaunchPresenter @Inject constructor() : BasePresenter<LaunchContract.View>(), LaunchContract.Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    val disposable = Completable.merge(listOf(timer()))
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.startPrefetchService() }
        .doOnSubscribe { view.displayLaunchAnimation(LAUNCH_SCREEN_DURATION) }
        .subscribe {
          view.startMainActivity()
        }
    disposables?.addAll(disposable)
  }

  private fun timer(): Completable {
    return Single.timer(LAUNCH_SCREEN_DURATION, TimeUnit.MILLISECONDS).toCompletable()
  }

  companion object {

    const val LAUNCH_SCREEN_DURATION = 3000L
  }
}