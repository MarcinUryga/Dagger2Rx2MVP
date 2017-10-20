package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.PodcastResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by marci on 2017-10-18.
 */
class GetPodcastsUseCase @Inject constructor(
    private val podcastApi: PodcastApi
) {

  fun get(): Observable<PodcastResponse> {
    return podcastApi.getPodcasts()
  }
}