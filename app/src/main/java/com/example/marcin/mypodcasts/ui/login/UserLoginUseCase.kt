package com.example.marcin.mypodcasts.ui.login

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by marci on 2017-10-16.
 */
class UserLoginUseCase @Inject constructor(
    private val podcastApi: PodcastApi
) {

  fun tryToLogin(registerRequest: RegisterRequest): Observable<UserResponse> {
    return podcastApi.getLogin(registerRequest.username, registerRequest.password)
  }
}