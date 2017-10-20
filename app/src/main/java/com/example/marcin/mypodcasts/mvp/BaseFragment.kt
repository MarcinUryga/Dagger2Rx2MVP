package com.example.marcin.mypodcasts.mvp

import android.app.Fragment
import android.os.Bundle
import android.view.View
import javax.inject.Inject

/**
 * Created by marci on 2017-10-17.
 */
abstract class BaseFragment<P : MvpPresenter> : Fragment() {

  @Inject lateinit var presenter: P

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.onViewCreated()
  }

  override fun onStart() {
    super.onStart()
    presenter.start()
  }

  override fun onResume() {
    super.onResume()
    presenter.resume()
  }

  override fun onPause() {
    super.onPause()
    presenter.pause()
  }

  override fun onStop() {
    super.onStop()
    presenter.stop()
  }
}