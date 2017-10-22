package com.example.marcin.mypodcasts.ui.search_podcasts

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.model.Subscription
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.storage.DataManager
import com.example.marcin.mypodcasts.storage.UserSharedPref
import com.example.marcin.mypodcasts.ui.search_podcasts.events.AddPodcastEvent
import com.example.marcin.mypodcasts.ui.search_podcasts.events.PodcastDetailsEvent
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2017-10-17.
 */

@ScreenScope
class SearchPodcastsPresenter @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val postPodcastUseCase: PostPodcastUseCase,
    private val sharedPreferences: SharedPreferences,
    private val dataManager: DataManager,
    private val bus: Bus
) : BasePresenter<SearchPodcastsContract.View>(), SearchPodcastsContract.Presenter {

  private val userSharedPref = UserSharedPref(sharedPreferences)

  override fun onViewCreated() {
    super.onViewCreated()
    bus.register(this)
  }

  override fun loadPodcasts() {
    val disposable = getPodcastsUseCase.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe({ response ->
          view.showPodcasts(response.results, bus)
        }, { error ->
          Timber.d("Error " + error.localizedMessage)
        })
    disposables?.add(disposable)
  }

  @Subscribe
  fun onPodcastDetailsEvent(event: PodcastDetailsEvent) {
    Timber.d("${event.podcast.podcastId} dffssdfsdf")
  }

  @Subscribe
  fun onAddPodcastEvent(event: AddPodcastEvent) {
    val user = dataManager.getUser(userSharedPref.getUserId())
    val disposable = postPodcastUseCase
        .post(
            createSubscription(
                podcast = event.podcast,
                userId = user.id
            ),
            sessionToken = user.sessionToken
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response ->
          Timber.d("Added: ${response.toString()}")
        }, { error ->
          Timber.d(error.localizedMessage)
        })
    disposables?.add(disposable)

  }

  private fun handleResponse(androidList: List<Podcast>) {

    Timber.d("${androidList.size}")
  }

  private fun createSubscription(podcast: Podcast, userId: String): Subscription {
    val aclJson = JsonObject()
    aclJson.add("read", JsonPrimitive(true))
    aclJson.add("write", JsonPrimitive(true))
    return Subscription(
        userId =userId,
        podcastId = podcast.podcastId,
        acl = aclJson
    )
  }
}