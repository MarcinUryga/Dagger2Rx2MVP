package com.example.marcin.mypodcasts.services.player_service

import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import io.reactivex.Observable

/**
 * Created by marci on 2018-05-30.
 */
interface PlayerContract {

  interface Service {

    fun createNotification(episode: Episode)

    fun getPlayState(played: Boolean)

//    fun getEpisodeDetails(episode: Episode)
  }

  interface Presenter {

    fun getEpisode(podcastId: Long, episodeId: Long)

    fun getPublishSubject(): Observable<Episode>

    fun handlePlayOrPause(): Boolean

    fun getIsPlayedState(): Boolean
  }
}