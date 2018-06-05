package com.example.marcin.mypodcasts.services.player_service

import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import io.reactivex.Observable

/**
 * Created by marci on 2018-05-30.
 */
interface PlayerContract {

  interface Service {

    fun createNotification(episode: Episode)

    fun getTicks(ticks: Pair<Int, Int>)
  }

  interface Presenter {

    fun setPlayerState(state: PlayerState)

    fun getTicksPublishSubject(): Observable<Pair<Int, Int>>

    fun handlePlayOrPause(epiode: Episode): PlayerState

    fun getPlayerState(): PlayerState

    fun destroy()

    fun preparePlayerManager(audioUrl: String)
  }
}