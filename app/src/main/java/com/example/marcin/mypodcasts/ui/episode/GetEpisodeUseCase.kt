package com.example.marcin.mypodcasts.ui.episode

import com.example.marcin.mypodcasts.repository.PodcastRepository
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2018-05-19.
 */
class GetEpisodeUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository
) {

  fun get(podcastId: Long, episodeId: Long): Single<Episode> {
    return podcastRepository.getEpisodeDetails(podcastId, episodeId)
        .map { episodeDetails ->
          Episode(
              audioUrl = episodeDetails.audioUrl,
              episodeId = episodeDetails.episodeId,
              title = episodeDetails.title,
              duration = episodeDetails.duration,
              description = episodeDetails.episodeDescription,
              imageUrl = episodeDetails.imageUrl
          )
        }
  }
}