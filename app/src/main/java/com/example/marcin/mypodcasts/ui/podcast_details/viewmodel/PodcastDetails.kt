package com.example.marcin.mypodcasts.ui.podcast_details.viewmodel

/**
 * Created by marci on 2018-05-14.
 */
data class PodcastDetails(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String,
    val episodes: List<Episode>
)