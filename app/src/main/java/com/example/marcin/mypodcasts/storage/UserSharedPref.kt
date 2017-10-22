package com.example.marcin.mypodcasts.storage

import android.content.SharedPreferences

/**
 * Created by marci on 2017-10-15.
 */
class UserSharedPref(
    private val sharedPreferences: SharedPreferences
) {

  private val storage = sharedPreferences.edit()

  fun saveSessionToken(userId: String) {
    storage.putString(USER_ID, userId).apply()
  }

  fun getUserId(): String {
    return sharedPreferences.getString(USER_ID, "")
  }

  fun logged() = sharedPreferences.getString(USER_ID, "").isNotEmpty()

  fun clearUserSharedPref() {
    storage
        .clear()
        .apply()
  }

  companion object {
    const val USER_ID = "userId"
  }
}