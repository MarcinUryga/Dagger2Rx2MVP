package com.example.marcin.mypodcasts.ui.main

import com.example.marcin.mypodcasts.storage.UserSharedPref
import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView

/**
 * Created by marci on 2017-10-15.
 */
interface MainContract {

  interface View : MvpView {

    fun openLoginActivity(userSharedPref: UserSharedPref)
  }

  interface Presenter : MvpPresenter {

    fun logoutUser()
  }
}