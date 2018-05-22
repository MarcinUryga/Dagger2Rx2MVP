package com.example.marcin.mypodcasts.api

import com.example.marcin.mypodcasts.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by MARCIN on 2017-10-15.
 */
interface PodcastApi {

  @POST("users")
  fun postRegister(@Body request: RegisterRequest): Single<UserResponse>

  @GET("login")
  fun getLogin(@Query("username") username: String, @Query("password") password: String): Observable<UserResponse>

  @GET("classes/Podcast")
  fun getPodcasts(): Single<PodcastsResponse>

  @GET("classes/Episode")
  fun getEpisodes(@Query("limit") limit: Int = 1000): Single<EpisodesResponse>

  @GET("classes/Episode")
  fun getEpisodesByPodcastId(@Query("podcastId") podcastId: Long): Single<EpisodesResponse>

  @GET("classes/Episode")
  fun getEpisodesById(@Query("episodeId") episodeId: Long): Single<Episode>
}

