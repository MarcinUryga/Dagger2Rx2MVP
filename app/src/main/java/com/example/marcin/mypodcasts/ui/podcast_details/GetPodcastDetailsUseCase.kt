package com.example.marcin.mypodcasts.ui.podcast_details

import com.example.marcin.mypodcasts.repository.PodcastRepository
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.PodcastDetails
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2018-05-14.
 */
class GetPodcastDetailsUseCase @Inject constructor(
    private val podcastRepository: PodcastRepository
) {

  private val testList = mutableListOf<Int>()

  fun getTest(): Flowable<MutableList<Int>> {
    return Flowable.just(testList)
        .flatMap {
          if (testList.isEmpty()) {
            testList.add(2)
            testList.add(6)
            testList.add(3)
            testList.add(1)
            getTest()
          }
          return@flatMap Flowable.just(testList)
        }
  }

  fun get(podcastId: Long): Single<PodcastDetails> {
    return podcastRepository.getPodcastDetails(podcastId)
        .map {
          PodcastDetails(
              id = it.podcast.podcastId,
              title = it.podcast.title,
              description = it.podcast.description,
              imageUrl = it.podcast.fullUrl,
              episodes = it.episodes.map {
                Episode(
                    podcastId = it.podcastId,
                    episodeId = it.episodeId,
                    title = it.title,
                    audioUrl = it.audioUrl,
                    description = it.description,
                    duration = it.duration
                )
              }.sortedBy { it.title }
          )
        }
  }
}