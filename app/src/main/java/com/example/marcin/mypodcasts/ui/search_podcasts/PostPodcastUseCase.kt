package com.example.marcin.mypodcasts.ui.search_podcasts

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.Subscription
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by marci on 2017-10-22.
 */
class PostPodcastUseCase @Inject constructor(
    private val podcastApi: PodcastApi
) {
  fun post(subscription: Subscription, sessionToken: String): Single<Subscription> {
    return podcastApi.postSubscription(subscription, sessionToken)
  }
}