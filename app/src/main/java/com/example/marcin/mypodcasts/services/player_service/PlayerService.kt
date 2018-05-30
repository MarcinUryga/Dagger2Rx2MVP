package com.example.marcin.mypodcasts.services.player_service

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.support.v4.app.NotificationCompat
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.ui.episode.EpisodeActivity
import com.example.marcin.mypodcasts.ui.episode.EpisodeIdParams
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2018-05-30.
 */
class PlayerService : Service(), PlayerContract.Service {

  @Inject lateinit var presenter: PlayerContract.Presenter
  private val binder = PlayerBinder()

  override fun onBind(intent: Intent?) = binder

  override fun onCreate() {
    AndroidInjection.inject(this)
    super.onCreate()
    Timber.d("onCreateeeeeeeeeeeeeeeeeeeee!!!!!!!!!!")
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Timber.d("onStartCommandddddddddddddddddd!!!!!!!!!!")
    if (intent != null) {
      presenter.getEpisode(
          podcastId = intent.getLongExtra(PodcastIdParam.PODCAST_ID, 0L),
          episodeId = intent.getLongExtra(EpisodeIdParams.EPISODE_ID, 0L)
      )
    }
    return super.onStartCommand(intent, flags, startId)
  }

  override fun createNotification(episode: Episode) {
    val notificationBuilder = NotificationCompat.Builder(baseContext)
        .setContentTitle(episode.title)
        .setContentText(episode.description)
        .setSmallIcon(R.drawable.ic_music_note_black)
        .setContentIntent(getEpisodePendingIntent(episode.podcastId, episode.episodeId))
        .build()
    startForeground(1, notificationBuilder)
  }

  override fun getPlayState(played: Boolean) {

  }

  private fun getEpisodePendingIntent(podcastId: Long, episodeId: Long): PendingIntent {
    return PendingIntent.getActivity(
        baseContext,
        0,
        EpisodeActivity.newIntent(baseContext, PodcastIdParam(podcastId), EpisodeIdParams(episodeId)),
        FLAG_CANCEL_CURRENT
    )
  }

  fun getPublishSubject() = presenter.getPublishSubject()

  fun playOrPause() = presenter.handlePlayOrPause()

  fun getPlayState() = presenter.getIsPlayedState()

  companion object {

    fun newIntent(context: Context, podcastIdParam: PodcastIdParam, episodeIdParams: EpisodeIdParams): Intent {
      return Intent(context, PlayerService::class.java)
          .putExtras(podcastIdParam.data)
          .putExtras(episodeIdParams.data)
    }
  }

  inner class PlayerBinder : Binder() {

    fun getService(): PlayerService {
      return this@PlayerService
    }
  }
}