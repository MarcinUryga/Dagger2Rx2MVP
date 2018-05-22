package com.example.marcin.mypodcasts.ui.search_podcasts


import com.example.marcin.mypodcasts.model.realm.PodcastRealmObject
import com.example.marcin.mypodcasts.repository.PodcastRepository
import com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel.Podcast
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2017-10-18.
 */
class GetPodcastsUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository,
    private val podcastManager: PodcastManager
) {

  fun get(): Single<List<Podcast>> {
    return Single.fromCallable {
      podcastRepository.get(PodcastRealmObject::class) {
        val podcasts = findAll()
        podcasts.map {
          Podcast(
              id = it.podcastId,
              title = it.title,
              numberOfEpisodes = it.numberOfEpisodes,
              description = it.description,
              fullUrl = it.fullUrl,
              thumbUrl = it.thumbUrl,
              isSubscribed = podcastManager.get(it.podcastId.toString()) ?: false
          )
        }
      }
    }
  }
}