package com.example.marcin.mypodcasts.common

import android.content.Context
import android.net.ConnectivityManager
import com.example.marcin.mypodcasts.PodcastApplication
import javax.inject.Inject

/**
 * Created by marci on 2018-05-11.
 */
class NetworkConnection @Inject constructor(
    private val applicationContext: PodcastApplication
) {

  fun isOnline(): Boolean {
    val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
  }
}