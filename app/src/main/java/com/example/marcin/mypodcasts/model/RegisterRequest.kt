package com.example.marcin.mypodcasts.model

/**
 * Created by MARCIN on 2017-10-15.
 */
data class RegisterRequest(

        val username: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val password: String
)