package com.example.marcin.mypodcasts.services.prefetch_data

/**
 * Created by marci on 2018-05-14.
 */
interface PrefetchContract {

  interface Service

  interface Presenter {

    fun prefetch()

    fun destroy()
  }
}