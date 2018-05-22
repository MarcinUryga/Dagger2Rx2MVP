package com.example.marcin.mypodcasts.ui.main

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.common.LoginManager
import javax.inject.Inject

/**
 * Created by marci on 2017-10-15.
 */

@ScreenScope
class MainPresenter @Inject constructor(
    private val loginManager: LoginManager
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

  override fun logoutUser() {
    loginManager.logout()
    view.openLoginActivity()
  }
}