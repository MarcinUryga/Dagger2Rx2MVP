package com.example.marcin.mypodcasts.repository

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.*
import com.example.marcin.mypodcasts.model.realm.EpisodeRealmObject
import com.example.marcin.mypodcasts.model.realm.PodcastRealmObject
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.realm.RealmObject
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2018-05-11.
 */
class PodcastRepository @Inject constructor(
    private val podcastApi: PodcastApi,
    realmManager: RealmManager
) : RealmRepository(realmManager) {

  fun savePodcasts(): Completable {
    return getPodcastsFromApi().doOnSuccess { podcasts ->
      copyOrUpdate(transformPodcastToRealmObject(podcasts.results))
    }.toCompletable()
  }

  fun saveEpisodes(): Completable {
    return getEpisodesFromApi().doOnSuccess { episodes ->
      copyOrUpdate(transformEpisodesToRealmObjects(episodes.results))
    }.doOnError { error ->
      Timber.d(error.localizedMessage)
    }.toCompletable()
  }


  private fun getEpisodesFromApi(): Single<EpisodesResponse> {
    return podcastApi.getEpisodes()
  }

  fun getPodcastsFromApi(): Single<PodcastsResponse> {
    return podcastApi.getPodcasts()
  }

  fun getEpisodeDetails(podcastId: Long, episodeId: Long): Single<EpisodeDetails> {
    return Single.zip(
        getPodcast(podcastId),
        getEpisode(episodeId),
        BiFunction { podcast, episode ->
          EpisodeDetails(
              podcastId = podcast.podcastId,
              podcastTitle = podcast.title,
              podcastDescription = podcast.description,
              audioUrl = episode.audioUrl,
              episodeId = episode.episodeId,
              title = episode.title,
              episodeDescription = episode.description,
              duration = episode.duration,
              imageUrl = podcast.fullUrl
          )
        }
    )
  }

  private fun getEpisode(episodeId: Long): Single<Episode> {
    return Single.fromCallable {
      get(EpisodeRealmObject::class) {
        val episode = equalTo("episodeId", episodeId).findFirst()
        transformToEpisode(episode)
      }
    }
  }

  fun getPodcastDetails(id: Long): Single<PodcastDetails> {
    return Single.zip(
        getPodcast(id),
        getEpisodes(id),
        BiFunction { podcast, episodes ->
          PodcastDetails(
              podcast = podcast,
              episodes = episodes
          )
        }
    )
  }

  private fun transformToEpisode(episode: EpisodeRealmObject?): Episode {
    return Episode(
        episodeId = episode?.episodeId.let { it!! },
        title = episode?.title.let { it!! },
        audioUrl = episode?.audioUrl.let { it!! },
        description = episode?.description.let { it!! },
        duration = episode?.duration.let { it!! },
        createdAt = episode?.createdAt.let { it!! },
        podcastId = episode?.podcastId.let { it!! },
        updatedAt = episode?.updatedAt.let { it!! }
    )
  }

  private fun getPodcast(id: Long): Single<Podcast?> {
    return Single.fromCallable {
      get(PodcastRealmObject::class) {
        val podcast = equalTo("podcastId", id).findFirst()
        Podcast(
            podcastId = podcast?.podcastId.let { it!! },
            numberOfEpisodes = podcast?.numberOfEpisodes ?: 0,
            title = podcast?.title ?: "",
            description = podcast?.description ?: "",
            fullUrl = podcast?.fullUrl ?: "",
            thumbUrl = podcast?.thumbUrl ?: ""
        )
      }
    }
  }

  private fun getEpisodes(id: Long): Single<List<Episode>> {
    return Single.fromCallable {
      get(EpisodeRealmObject::class) {
        val episodes = equalTo("podcastId", id).findAll()
        episodes.map { transformToEpisode(it) }
      }
    }
  }

  private fun transformPodcastToRealmObject(podcasts: List<Podcast>): List<RealmObject> {
    return podcasts.map {
      PodcastRealmObject().apply {
        podcastId = it.podcastId
        numberOfEpisodes = it.numberOfEpisodes
        title = it.title
        description = it.description
        fullUrl = it.fullUrl
        thumbUrl = it.thumbUrl
      }
    }
  }

  private fun transformEpisodesToRealmObjects(episodes: List<Episode>): List<RealmObject> {
    return episodes.map {
      EpisodeRealmObject().apply {
        episodeId = it.episodeId
        title = it.title
        audioUrl = it.audioUrl
        description = it.description
        duration = it.duration
        updatedAt = it.updatedAt
        createdAt = it.createdAt
        podcastId = it.podcastId
      }
    }
  }
}