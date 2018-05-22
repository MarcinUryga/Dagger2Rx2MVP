package com.example.marcin.mypodcasts.ui.my_podcasts

import com.example.marcin.mypodcasts.model.realm.PodcastRealmObject
import com.example.marcin.mypodcasts.repository.PodcastRepository
import com.example.marcin.mypodcasts.ui.my_podcasts.viewmodel.Podcast
import com.example.marcin.mypodcasts.ui.search_podcasts.PodcastManager
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2018-05-13.
 */
class GetSubscribedPodcastsUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository,
    private val podcastManager: PodcastManager
) {

  fun get(): Single<List<Podcast>> {
    return Single.fromCallable {
      podcastRepository.get(PodcastRealmObject::class) {
        val podcasts = `in`("podcastId", podcastManager.getAll()
            .map { it.key.toLong() }.toTypedArray())
            .findAll()
        podcasts.map {
          Podcast(
              id = it.podcastId,
              title = it.title,
              imageUrl = it.fullUrl
          )
        }
      }
    }
  }
}