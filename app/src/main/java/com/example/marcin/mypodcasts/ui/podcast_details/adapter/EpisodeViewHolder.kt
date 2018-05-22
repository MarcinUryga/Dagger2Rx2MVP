package com.example.marcin.mypodcasts.ui.podcast_details.adapter

import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.ui.common.HtmlUtils
import com.example.marcin.mypodcasts.ui.podcast_details.viewmodel.Episode
import kotlinx.android.synthetic.main.episode_item.view.*
import kotlinx.android.synthetic.main.expandable_text_layout.view.*

/**
 * Created by marci on 2018-05-15.
 */
class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(episode: Episode) {
    with(itemView) {
      episodeName.text = episode.title
      durationTextView.text = episode.duration
    /*  expandableTextView.text = HtmlUtils.createHtml(episode.description)
      initExpandableLayout()*/
    }
  }

  private fun initExpandableLayout() {
    with(itemView) {
      expandableTextView.movementMethod = LinkMovementMethod.getInstance()
      expandableTextView.setInterpolator(OvershootInterpolator())
      Linkify.addLinks(expandableTextView, Linkify.WEB_URLS)
      toggleButton.text = context.getString(R.string.more)
      toggleButton.setOnClickListener {
        toggleButton.text = context.getString(if (expandableTextView.isExpanded) R.string.more else R.string.less)
        expandableTextView.toggle()
      }
    }
  }

  companion object {
    fun create(parent: ViewGroup): EpisodeViewHolder {
      return EpisodeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false))
    }
  }
}