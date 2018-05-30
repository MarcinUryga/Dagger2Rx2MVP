package com.example.marcin.mypodcasts.ui.episode.viewmodel

/**
 * Created by marci on 2018-05-19.
 */
data class Episode(
    val podcastId: Long,
    val episodeId: Long,
    val audioUrl: String,
    val title: String,
    val duration: String,
    val description: String,
    val imageUrl: String
)