package com.example.marcin.mypodcasts.ui.main

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.storage.DataManager
import com.example.marcin.mypodcasts.storage.UserSharedPref
import javax.inject.Inject

/**
 * Created by marci on 2017-10-15.
 */

@ScreenScope
class MainPresenter @Inject constructor(
    sharedPreferences: SharedPreferences,
    private val dataManager: DataManager

) : BasePresenter<MainContract.View>(), MainContract.Presenter {
  private val userStorage = UserSharedPref(sharedPreferences)

  override fun logoutUser() {
    clearStorage()
    view.openLoginActivity(userStorage)
  }

  private fun clearStorage() {
    dataManager.deleteUser(userStorage.getUserId())
    userStorage.clearUserSharedPref()
  }
}