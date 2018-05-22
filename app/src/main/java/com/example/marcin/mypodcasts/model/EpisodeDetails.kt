package com.example.marcin.mypodcasts.model

/**
 * Created by marci on 2018-05-19.
 */
data class EpisodeDetails(
    val podcastId: Long,
    val podcastTitle: String,
    val podcastDescription: String,
    val audioUrl: String,
    val episodeId: Long,
    val episodeDescription: String,
    val title: String,
    val duration: String,
    val imageUrl: String
)