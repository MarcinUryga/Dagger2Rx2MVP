package com.example.marcin.mypodcasts.ui.search_podcasts.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel.Podcast
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.podcast_item.view.*
import kotlinx.android.synthetic.main.subscribed_podcast_item.view.*

/**
 * Created by marci on 2017-10-20.
 */
class PodcastAdapter : RecyclerView.Adapter<PodcastViewHolder>() {

  private val subscriptionPublishSubject = PublishSubject.create<Podcast>()
  private val podcasts = mutableListOf<Podcast>()

  fun addPodcasts(podcastsList: List<Podcast>) {
    this.podcasts.addAll(podcastsList)
    notifyDataSetChanged()
  }

  fun update(podcast: Podcast) {
    val iterator = podcasts.iterator()
    var index = 0
    while (iterator.hasNext()) {
      val current = iterator.next()
      if (current.id == podcast.id) {
        notifyItemChanged(index)
      }
      index++
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PodcastViewHolder.create(parent)

  override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
    val podcast = podcasts[position]
    holder.bind(podcast)

    holder.itemView.actionButton.setOnClickListener {
      subscriptionPublishSubject.onNext(podcast)
    }
  }

  fun getClickedSubscription(): Observable<Podcast> = subscriptionPublishSubject

  override fun getItemCount() = podcasts.size
}
