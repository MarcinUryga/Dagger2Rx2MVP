package com.example.marcin.mypodcasts.ui.episode

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.util.Linkify
import android.widget.Toast
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.services.player_service.PlayerService
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_episode.*

/**
 * Created by marci on 2018-05-18.
 */
class EpisodeActivity : BaseActivity<EpisodeContract.Presenter>(), EpisodeContract.View {

  private var playerService: PlayerService? = null
  private var playerConnection = object : ServiceConnection {

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
      playerService = (service as PlayerService.PlayerBinder).getService()
      presenter.getEpisode(playerService?.getPublishSubject())
    }

    override fun onServiceDisconnected(name: ComponentName) {
      playerService = null
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_episode)
    actionButton.isSelected = false
    setSupportActionBar(toolbar)
    descriptionTextView.movementMethod = ScrollingMovementMethod()
  }

  override fun showToast(episode: Episode) {
    Toast.makeText(baseContext, episode.toString(), Toast.LENGTH_SHORT).show()
  }

  override fun startPlayerService(podcastId: Long, episodeId: Long) {
    val serviceIntent = PlayerService.newIntent(baseContext, PodcastIdParam(podcastId), EpisodeIdParams(episodeId))
    bindService(serviceIntent, playerConnection, Context.BIND_AUTO_CREATE)
    startService(serviceIntent)
  }

  override fun incrementSeekBar(progress: Int) {
    if (actionButton.isSelected) {
      seekBar.progress += progress
      elapsedTime.text = seekBar.progress.toString()
      remainingTime.text = (seekBar.max - seekBar.progress).toString()
      presenter.handleIncrement()
    }
  }

  override fun showProgressBar() {
//    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
//    progressBar.visibility = View.INVISIBLE
  }

  override fun showEpisodeDetails(episode: Episode) {
    Picasso.with(baseContext).load(episode.imageUrl).placeholder(getDrawable(R.drawable.podcast_image)).into(podcastImage)
    toolbar.title = episode.title
    descriptionTextView.text = episode.description
    descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
    Linkify.addLinks(descriptionTextView, Linkify.WEB_URLS)
    updatePlayButtonState(playerService?.getPlayState().let { it!! })
    actionButton.setOnClickListener {
      updatePlayButtonState(playerService?.playOrPause().let { it!! })
    }
    seekBar.max = transformEpisodeDurationToSeconds(episode.duration)
    elapsedTime.text = "0"
    remainingTime.text = seekBar.max.toString()
  }

  override fun setProgressOnSeekBar(startTime: Int) {
    seekBar.progress = startTime
  }

  override fun updatePlayButtonState(isSelected: Boolean) {
    if (isSelected) {
      actionButton.setImageDrawable(getDrawable(PlayButtonIcon.PAUSE.id))
    } else {
      actionButton.setImageDrawable(getDrawable(PlayButtonIcon.PLAY.id))
    }
    actionButton.isSelected = isSelected
  }

  private fun transformEpisodeDurationToSeconds(duration: String): Int {
    val splitedTime = duration.split(":")
    return splitedTime[2].toInt() + (60 * splitedTime[1].toInt()) + (3600 * splitedTime[0].toInt())
  }

  companion object {

    fun newIntent(context: Context, podcastIdParam: PodcastIdParam, episodeIdParams: EpisodeIdParams): Intent {
      return Intent(context, EpisodeActivity::class.java)
          .putExtras(podcastIdParam.data)
          .putExtras(episodeIdParams.data)
    }
  }
}