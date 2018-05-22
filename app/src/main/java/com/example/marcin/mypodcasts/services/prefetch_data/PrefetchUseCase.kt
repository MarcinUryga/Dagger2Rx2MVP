package com.example.marcin.mypodcasts.services.prefetch_data

import com.example.marcin.mypodcasts.repository.PodcastRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by marci on 2018-05-11.
 */
class PrefetchUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository
) {

  fun prefetch(): Completable {
    return podcastRepository.savePodcasts()
        .mergeWith(podcastRepository.saveEpisodes())
  }
}