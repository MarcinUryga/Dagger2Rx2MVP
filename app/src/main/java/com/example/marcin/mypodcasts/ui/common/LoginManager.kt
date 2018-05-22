package com.example.marcin.mypodcasts.ui.common

import com.example.marcin.mypodcasts.sharedprefs.PrefsManager
import javax.inject.Inject

/**
 * Created by marci on 2018-05-12.
 */
class LoginManager @Inject constructor(
    prefsManager: PrefsManager
) {

  private val sharedPrefs = prefsManager.getPreferencesStorage(PrefsManager.USER)

  fun login(userId: String, sessionToken: String) {
    sharedPrefs.put(USER_ID, userId)
    sharedPrefs.put(SESSION_TOKEN, sessionToken)
  }

  fun getUserId() = sharedPrefs.get<String>(USER_ID)

  fun getSessionToken() = sharedPrefs.get<String>(SESSION_TOKEN)

  fun isLogged() = !getUserId().isNullOrEmpty()

  fun logout() {
    sharedPrefs.put(USER_ID, null)
    sharedPrefs.put(SESSION_TOKEN, null)
  }

  companion object {
    const val USER_ID = "userId"
    const val SESSION_TOKEN = "sessionToken"
  }
}