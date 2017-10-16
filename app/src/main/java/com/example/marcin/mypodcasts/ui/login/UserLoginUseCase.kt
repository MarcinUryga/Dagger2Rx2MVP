package com.example.marcin.mypodcasts.ui.login

import com.example.marcin.mypodcasts.api.PodcastApi
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import io.reactivex.Observable
import retrofit2.Call


/**
 * Created by marci on 2017-10-16.
 */
class UserLoginUseCase(
    private val podcastApi: PodcastApi
) {

  private var loginCall: Call<UserResponse>? = null

  /* fun tryToLogin(username: String, password: String) {

     if (loginCall == null) {
       loginCall = podcastApi.getLogin(username, password)

       loginCall!!.enqueue(object : Callback<UserResponse> {
         override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
           loginCall = null
           if (response.isSuccessful()) {
             val body = response.body()
             Log.d(LoginActivity::class.java.simpleName, "Resp: " + body.toString())

           } else {
             val responseBody = response.errorBody()
             Log.d(LoginActivity::class.java.simpleName, "Resp: " + responseBody.toString())

           }
         }

         override fun onFailure(call: Call<UserResponse>, t: Throwable) {
           loginCall = null
           Log.d(LoginActivity::class.java.simpleName, "Resp: " + t.localizedMessage)

         }
       })
     }
   }*/
  fun tryToLogin(registerRequest: RegisterRequest): Observable<UserResponse> {
    return podcastApi.getLogin(registerRequest.username, registerRequest.password)
  }
}