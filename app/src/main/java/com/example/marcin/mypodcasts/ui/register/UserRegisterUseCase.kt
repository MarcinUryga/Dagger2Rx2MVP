package com.example.marcin.mypodcasts.ui.register

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import io.reactivex.Single

/**
 * Created by marci on 2017-10-13.
 */

class UserRegisterUseCase(
    private val podcastApi: PodcastApi
) {

  fun tryToRegister(registerRequest: RegisterRequest): Single<UserResponse> {
    return podcastApi.postRegister(registerRequest)
  }
}