package com.example.marcin.mypodcasts.model

/**
 * Created by marci on 2018-05-15.
 */
data class PodcastDetails(
    val podcast: Podcast,
    val episodes: List<Episode>
)