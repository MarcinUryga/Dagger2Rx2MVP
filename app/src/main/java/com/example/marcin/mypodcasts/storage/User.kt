package com.example.marcin.mypodcasts.storage

/**
 * Created by marci on 2017-10-22.
 */
data class User(
    val id: String,
    val sessionToken: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String
)