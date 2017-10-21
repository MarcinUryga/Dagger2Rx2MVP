package com.example.marcin.mypodcasts.storage

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.model.UserResponse

/**
 * Created by marci on 2017-10-15.
 */
class UserStorage(
    val sharedPreferences: SharedPreferences
) {

  val storage = sharedPreferences.edit()

  fun saveToStorage(userResponse: UserResponse) {
    storage.putString(SESSION_TOKEN, userResponse.sessionToken)
    storage.putString(USER_ID, userResponse.objectId)
    storage.putString(FIRST_NAME, userResponse.firstName)
    storage.putString(LAST_NAME, userResponse.lastName)
    storage.putString(USERNAME, userResponse.username)
    storage.putString(EMAIL, userResponse.email)
    storage.apply()
  }

  fun logged() = sharedPreferences.getString(SESSION_TOKEN, "").isNotEmpty()

  fun clearUserStorage() {
    storage
        .clear()
        .apply()
  }

  fun getSessionToken() = sharedPreferences.getString(USER_ID, "")

  fun getUserId() = sharedPreferences.getString(USER_ID, "")

  fun getFirstName() = sharedPreferences.getString(FIRST_NAME, "")

  fun getLastName() = sharedPreferences.getString(LAST_NAME, "")

  fun getUsername() = sharedPreferences.getString(USERNAME, "")

  fun getEmail() = sharedPreferences.getString(EMAIL, "")

  companion object {
    const val SESSION_TOKEN = "sessionToken"
    const val USERNAME = "username"
    const val EMAIL = "email"
    const val FIRST_NAME = "firstName"
    const val LAST_NAME = "lastName"
    const val USER_ID = "userId"
  }
}