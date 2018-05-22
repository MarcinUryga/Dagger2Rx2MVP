package com.example.marcin.mypodcasts.ui.launch

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView

/**
 * Created by marci on 2018-05-11.
 */
interface LaunchContract {

  interface View : MvpView {

    fun startMainActivity()

    fun displayLaunchAnimation(launchScreenDuration: Long)

    fun startPrefetchService()
  }

  interface Presenter : MvpPresenter
}