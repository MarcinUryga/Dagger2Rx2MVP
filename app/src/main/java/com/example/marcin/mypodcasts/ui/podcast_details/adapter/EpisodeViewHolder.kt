package com.example.marcin.mypodcasts.ui.podcast_details.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.common.DateUtils
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.Episode
import kotlinx.android.synthetic.main.episode_item.view.*

/**
 * Created by marci on 2018-05-15.
 */
class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(episode: Episode) {
    with(itemView) {
      episodeName.text = episode.title
      durationTextView.text = DateUtils.secondsToTimeString(episode.duration.toInt())
    }
  }

  companion object {
    fun create(parent: ViewGroup): EpisodeViewHolder {
      return EpisodeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false))
    }
  }
}