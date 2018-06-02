package com.example.marcin.mypodcasts.ui.search_podcasts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel.Podcast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.podcast_item.view.*

/**
 * Created by marci on 2018-05-12.
 */
class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(podcast: Podcast) {
    with(itemView) {
      podcastTitle.text = podcast.title
      podcastTitle.textSize = 16f
      numberOfEpisodes.text = context.getString(R.string.number_of_episodes, podcast.numberOfEpisodes)
      numberOfEpisodes.textSize = 12f
      Picasso.with(context).load(podcast.thumbUrl).fit().placeholder(R.drawable.podcast_image).into(podcastImage)
      actionButton.isSelected = podcast.isSubscribed
      if (podcast.isSubscribed) {
        actionButton.text = context.getString(R.string.unsubscribe)
      } else {
        actionButton.text = context.getString(R.string.subscribe)
      }
    }
  }

  companion object {
    fun create(parent: ViewGroup): PodcastViewHolder {
      return PodcastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.podcast_item, parent, false))
    }

  }
}