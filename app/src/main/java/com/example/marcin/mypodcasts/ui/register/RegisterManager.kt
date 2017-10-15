package com.example.marcin.mypodcasts.ui.register

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import io.reactivex.Observable

/**
 * Created by marci on 2017-10-13.
 */

class RegisterManager(
        private val podcastApi: PodcastApi
) {

    fun tryToRegister(registerRequest: RegisterRequest): Observable<UserResponse> {
        return podcastApi.postRegister(registerRequest)
    }
}