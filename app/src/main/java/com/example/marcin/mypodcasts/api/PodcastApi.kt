package com.example.marcin.mypodcasts.api

import com.example.marcin.mypodcasts.model.PodcastsResponse
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.Subscription
import com.example.marcin.mypodcasts.model.UserResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by MARCIN on 2017-10-15.
 */
interface PodcastApi {

  @POST("classes/Subscription")
  fun postSubscription(@Body subscription: Subscription, @Header("X-Parse-Session-Token") token: String): Single<Subscription>

  @POST("users")
  fun postRegister(@Body request: RegisterRequest): Single<UserResponse>

  @GET("login")
  fun getLogin(@Query("username") username: String, @Query("password") password: String): Observable<UserResponse>

  @GET("classes/Podcast")
  fun getPodcasts(): Observable<PodcastsResponse>

}

