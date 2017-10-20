package com.example.marcin.mypodcasts.model

/**
 * Created by marci on 2017-10-18.
 */
data class Podcast(
    val podcastId: Long,
    val numberOfEpisodes: Int,
    val title: String,
    val description: String,
    val fullUrl: String,
    val thumbUrl: String
)