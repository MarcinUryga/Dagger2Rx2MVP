package com.example.marcin.mypodcasts.ui.episode.viewmodel

/**
 * Created by marci on 2018-05-19.
 */
data class Episode(
    val audioUrl: String,
    val episodeId: Long,
    val title: String,
    val duration: String,
    val description: String,
    val imageUrl: String
)