package com.example.marcin.mypodcasts.ui.main

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.UserStorage
import com.example.marcin.mypodcasts.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by marci on 2017-10-15.
 */

@ScreenScope
class MainPresenter @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

  private val userStorage = UserStorage(sharedPreferences)

  override fun onViewCreated() {
    super.onViewCreated()
    view.setTestText(userStorage.getEmail())
  }
}