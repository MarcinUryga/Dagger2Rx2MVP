package com.example.marcin.mypodcasts.ui.my_podcasts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.ui.my_podcasts.viewmodel.Podcast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.subscribed_podcast_item.view.*

/**
 * Created by marci on 2018-05-13.
 */
class SubscribedPodcastsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(podcast: Podcast) {
    with(itemView) {
      with(itemView) {
        podcastTitle.text = podcast.title
        podcastTitle.textSize = 16f
        Picasso.with(context).load(podcast.imageUrl).fit().placeholder(R.drawable.podcast_image).into(podcastImage)
      }
    }
  }

  companion object {
    fun create(parent: ViewGroup): SubscribedPodcastsViewHolder {
      return SubscribedPodcastsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.subscribed_podcast_item, parent, false))
    }
  }
}