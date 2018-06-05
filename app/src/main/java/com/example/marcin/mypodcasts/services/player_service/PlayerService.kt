package com.example.marcin.mypodcasts.services.player_service

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.support.v4.app.NotificationCompat
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.services.player_service.PlayerManager.Companion.CLOSE_PLAYER
import com.example.marcin.mypodcasts.ui.episode.EpisodeActivity
import com.example.marcin.mypodcasts.ui.episode.EpisodeIdParams
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import dagger.android.AndroidInjection
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
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    if (intent != null) {
      val audioUrl = intent.getStringExtra(getString(R.string.audio_url))
      if (audioUrl != null) {
        presenter.preparePlayerManager(audioUrl)
      }
      if (intent.action != null && intent.action == CLOSE_PLAYER) {
        stopSelf()
        stopForeground(true)
        sendBroadcast(Intent(CLOSE_PLAYER))
        onDestroy()
      }
    }
    return super.onStartCommand(intent, flags, startId)
  }

  override fun createNotification(episode: Episode) {
    val notificationBuilder = NotificationCompat.Builder(baseContext)
        .setContentTitle(episode.title)
        .setContentText(episode.description)
        .setSmallIcon(R.drawable.ic_music_note_black)
        .setContentIntent(getEpisodePendingIntent(episode.podcastId, episode.episodeId))
        .addAction(
            android.R.drawable.ic_menu_close_clear_cancel,
            getString(R.string.cancel),
            getCancelPendingIntent()
        ).build()
    startForeground(1, notificationBuilder)
  }

  private fun getCancelPendingIntent(): PendingIntent {
    val intent = Intent(this, PlayerService::class.java)
    intent.action = CLOSE_PLAYER
    return PendingIntent.getService(
        this,
        2,
        intent,
        FLAG_CANCEL_CURRENT
    )
  }

  override fun getTicks(ticks: Pair<Int, Int>) {
//    sendBroadcast(Intent(CLOSE_PLAYER).putExtra("ticks", ticks.first))
  }

  private fun getEpisodePendingIntent(podcastId: Long, episodeId: Long): PendingIntent {
    return PendingIntent.getActivity(
        baseContext,
        0,
        EpisodeActivity.newIntent(baseContext, PodcastIdParam(podcastId), EpisodeIdParams(episodeId)),
        FLAG_CANCEL_CURRENT
    )
  }

  fun setPlayerState(state: PlayerState) = presenter.setPlayerState(state)

  fun getTicksPublishSubject() = presenter.getTicksPublishSubject()

  fun prepareAudio(audioUrl: String) = presenter.preparePlayerManager(audioUrl)

  fun playOrPause(episode: Episode) = presenter.handlePlayOrPause(episode)

  fun getPlayState() = presenter.getPlayerState()

  override fun onDestroy() {
    super.onDestroy()
    presenter.destroy()
  }

  companion object {

    fun newIntent(context: Context): Intent {
      return Intent(context, PlayerService::class.java)
    }
  }

  inner class PlayerBinder : Binder() {

    fun getService(): PlayerService {
      return this@PlayerService
    }
  }
}