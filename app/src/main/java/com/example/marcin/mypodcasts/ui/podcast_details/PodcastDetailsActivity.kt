package com.example.marcin.mypodcasts.ui.podcast_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.OvershootInterpolator
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.ui.episode.EpisodeActivity
import com.example.marcin.mypodcasts.ui.episode.EpisodeIdParams
import com.example.marcin.mypodcasts.ui.podcast_details.adapter.EpisodesAdapter
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.PodcastDetails
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_podcast_details.*
import kotlinx.android.synthetic.main.content_podcast_details.*
import kotlinx.android.synthetic.main.expandable_text_layout.*


/**
 * Created by marci on 2017-10-20.
 */
class PodcastDetailsActivity : BaseActivity<PodcastDetailsContract.Presenter>(), PodcastDetailsContract.View {

  private val episodesAdapter = EpisodesAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_podcast_details)
    episodesRecyclerView.layoutManager = LinearLayoutManager(this)
    presenter.handleClickedEpisode(episodesAdapter.onClickedEpisode())
    expandableTextView.setInterpolator(OvershootInterpolator())
    toggleButton.setOnClickListener {
      toggleButton.text = getString(if (expandableTextView.isExpanded) R.string.more else R.string.less)
      expandableTextView.toggle()
    }
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
  }

  override fun showDetails(podcastDetails: PodcastDetails) {
    Picasso.with(this).load(podcastDetails.imageUrl).into(podcastImage)
    toolbar.title = podcastDetails.title
    expandableTextView.text = podcastDetails.description
    toggleButton.text = getString(R.string.more)
    episodesAdapter.addEpisodes(podcastDetails.episodes)
    episodesRecyclerView.adapter = episodesAdapter
  }

  companion object {
    fun newIntent(context: Context, podcastIdParam: PodcastIdParam): Intent {
      val intent = Intent(context, PodcastDetailsActivity::class.java)
      intent.putExtras(podcastIdParam.data)
      return intent
    }
  }

  override fun startEpisodeActivity(podcastId: Long, episodeId: Long) {
    startActivity(EpisodeActivity.newIntent(this, PodcastIdParam(podcastId), EpisodeIdParams(episodeId)))
  }
}
