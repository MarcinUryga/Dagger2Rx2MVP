package com.example.marcin.mypodcasts.model

/**
 * Created by marci on 2018-05-14.
 */
data class Episode(
    val episodeId: Long,
    val title: String,
    val description: String,
    val audioUrl: String,
    val duration: String,
    val podcastId: Long,
    val createdAt: String,
    var updatedAt: String
)