package com.example.marcin.mypodcasts.mvp

/**
 * Created by MARCIN on 2017-10-14.
 */
interface MvpPresenter {

    fun onViewCreated()

    fun start()

    fun resume()

    fun pause()

    fun stop()

    fun destroy()
}