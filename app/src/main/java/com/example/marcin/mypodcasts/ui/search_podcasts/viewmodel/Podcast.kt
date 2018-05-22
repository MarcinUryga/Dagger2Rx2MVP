package com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel

/**
 * Created by marci on 2017-10-20.
 */
data class Podcast(
    val id: Long,
    val title: String,
    val numberOfEpisodes: Int,
    val description: String,
    val fullUrl: String,
    val thumbUrl: String,
    var isSubscribed: Boolean
)