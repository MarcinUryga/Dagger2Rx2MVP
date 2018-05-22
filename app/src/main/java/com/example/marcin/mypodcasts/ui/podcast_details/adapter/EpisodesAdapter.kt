package com.example.marcin.mypodcasts.ui.podcast_details.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.Episode
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.episode_item.view.*

/**
 * Created by marci on 2018-05-15.
 */
class EpisodesAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {

  private val episodes = mutableListOf<Episode>()
  private val publishSubject = PublishSubject.create<Episode>()

  fun addEpisodes(episodeList: List<Episode>) {
    episodes.addAll(episodeList)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EpisodeViewHolder.create(parent)

  override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
    val episode = episodes[position]
    holder.bind(episode)
    holder.itemView.setOnClickListener {
      publishSubject.onNext(episode)
    }
  }

  fun onClickedEpisode(): Observable<Episode> = publishSubject

  override fun getItemCount() = episodes.size
}