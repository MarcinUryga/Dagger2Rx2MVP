package com.example.marcin.mypodcasts.api

import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by MARCIN on 2017-10-15.
 */
interface PodcastApi {

/*    @POST("users")
    fun postRegister(@Body request: RegisterRequest): Call<UserResponse> */

    @POST("users")
    fun postRegister(@Body request: RegisterRequest): Observable<UserResponse>
}

