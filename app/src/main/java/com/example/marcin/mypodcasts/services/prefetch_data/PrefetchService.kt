package com.example.marcin.mypodcasts.services.prefetch_data

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import dagger.android.AndroidInjection
import javax.inject.Inject

@SuppressLint("Registered")
/**
 * Created by marci on 2018-05-14.
 */
class PrefetchService : IntentService(this::class.toString()), PrefetchContract.Service {

  @Inject lateinit var presenter: PrefetchContract.Presenter

  override fun onCreate() {
    AndroidInjection.inject(this)
    super.onCreate()
  }

  override fun onHandleIntent(intent: Intent?) {
    presenter.prefetch()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.destroy()
  }

  companion object {

    fun newIntent(context: Context) = Intent(context, PrefetchService::class.java)
  }
}