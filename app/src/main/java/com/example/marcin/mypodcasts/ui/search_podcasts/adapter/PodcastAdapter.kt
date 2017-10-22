package com.example.marcin.mypodcasts.ui.search_podcasts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.ui.search_podcasts.events.AddPodcastEvent
import com.example.marcin.mypodcasts.ui.search_podcasts.events.PodcastDetailsEvent
import com.squareup.otto.Bus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.podcast_item.view.*

/**
 * Created by marci on 2017-10-20.
 */
class PodcastAdapter(
    private val podcastsList: List<Podcast>,
    private val bus: Bus
) : RecyclerView.Adapter<PodcastViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PodcastViewHolder.create(parent)

  override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
    val podcast = podcastsList[position]
    holder.bind(podcast)
    holder.itemView.setOnClickListener {
      Toast.makeText(it.context, "${podcast.title}  ${podcast.podcastId}", Toast.LENGTH_SHORT).show()
      bus.post(PodcastDetailsEvent(podcast))
    }
    holder.itemView.addButton.setOnClickListener(View.OnClickListener {
      bus.post(AddPodcastEvent(podcast))
    })

  }

  override fun getItemCount() = podcastsList.size
}

class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(podcast: Podcast) {
    itemView.podcastTitle.text = podcast.title
    itemView.podcastTitle.textSize = 16f
    itemView.numberOfEpisodes.text = itemView.context.getString(R.string.number_of_episodes, podcast.numberOfEpisodes)
    itemView.numberOfEpisodes.textSize = 12f
    Picasso.with(itemView.context).load(podcast.thumbUrl).placeholder(R.drawable.podcast_image).into(itemView.podcastImage)
  }

  companion object {
    fun create(parent: ViewGroup): PodcastViewHolder {
      return PodcastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.podcast_item, parent, false))
    }

  }
}
