package com.example.marcin.mypodcasts.ui.search_podcasts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.model.Podcast
import kotlinx.android.synthetic.main.podcast_item.view.*

/**
 * Created by marci on 2017-10-20.
 */
class PodcastAdapter(
    private val podcastsList: List<Podcast>
) : RecyclerView.Adapter<PodcastViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PodcastViewHolder.create(parent)

  override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) = holder.bind(podcastsList[position])

  override fun getItemCount() = podcastsList.size

}

class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(podcast: Podcast) {
    itemView.podcastTitle.text = podcast.title
    itemView.numberOfEpisodes.text = itemView.context.getString(R.string.number_of_episodes, podcast.numberOfEpisodes)
  }

  companion object {
    fun create(parent: ViewGroup): PodcastViewHolder {
      return PodcastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.podcast_item, parent, false))
    }

  }
}
