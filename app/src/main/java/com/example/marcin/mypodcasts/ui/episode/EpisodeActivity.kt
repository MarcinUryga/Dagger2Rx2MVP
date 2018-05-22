package com.example.marcin.mypodcasts.ui.episode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.util.Linkify
import android.view.View
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.ui.episode.viewmodel.Episode
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_episode.*

/**
 * Created by marci on 2018-05-18.
 */
class EpisodeActivity : BaseActivity<EpisodeContract.Presenter>(), EpisodeContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_episode)
    actionButton.isSelected = false
    setSupportActionBar(toolbar)
    descriptionTextView.movementMethod = ScrollingMovementMethod()
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
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
  }

  override fun showEpisodeDetails(episode: Episode, duration: Int) {
    Picasso.with(baseContext).load(episode.imageUrl).placeholder(getDrawable(R.drawable.podcast_image)).into(podcastImage)
    toolbar.title = episode.title
    descriptionTextView.text = episode.description
    descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
    Linkify.addLinks(descriptionTextView, Linkify.WEB_URLS)
    actionButton.setOnClickListener {
      presenter.handleActionButton(actionButton.isSelected)
    }
    seekBar.max = transformEpisodeDurationToSeconds(episode.duration)
    elapsedTime.text = "0"
    remainingTime.text = seekBar.max.toString()
  }

  override fun setProgressOnSeekBar(startTime: Int) {
    seekBar.progress = startTime
  }

  override fun updatePlayButtonState(isSelected: Boolean) {
    if (actionButton.isSelected) {
      actionButton.setImageDrawable(getDrawable(PlayButtonIcon.PLAY.id))
    } else {
      actionButton.setImageDrawable(getDrawable(PlayButtonIcon.PAUSE.id))
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