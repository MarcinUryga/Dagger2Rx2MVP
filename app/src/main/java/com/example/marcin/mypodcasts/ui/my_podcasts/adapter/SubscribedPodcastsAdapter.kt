package com.example.marcin.mypodcasts.ui.my_podcasts.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.marcin.mypodcasts.ui.my_podcasts.viewmodel.Podcast
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by marci on 2018-05-13.
 */
class SubscribedPodcastsAdapter : RecyclerView.Adapter<SubscribedPodcastsViewHolder>() {

  private val podcasts = mutableListOf<Podcast>()
  private val publishSubject = PublishSubject.create<Long>()

  fun addPodcasts(podcasts: List<Podcast>) {
    this.podcasts.addAll(podcasts)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubscribedPodcastsViewHolder.create(parent)

  override fun onBindViewHolder(holder: SubscribedPodcastsViewHolder, position: Int) {
    val podcast = podcasts[position]
    holder.bind(podcast)
    holder.itemView.setOnClickListener {
      publishSubject.onNext(podcast.id)
    }
  }

  fun getClickedPublishSubject(): Observable<Long> = publishSubject

  override fun getItemCount() = podcasts.size
}