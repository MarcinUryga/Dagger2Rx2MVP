package com.example.marcin.mypodcasts.ui.podcast_details.viewmodel

/**
 * Created by marci on 2018-05-14.
 */
data class Episode(
    val podcastId: Long,
    val episodeId: Long,
    val title: String,
    val audioUrl: String,
    val description: String,
    val duration: String
)