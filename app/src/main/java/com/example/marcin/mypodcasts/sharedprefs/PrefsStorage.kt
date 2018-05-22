package com.example.marcin.mypodcasts.sharedprefs

import android.content.SharedPreferences

/**
 * Created by marci on 2018-05-12.
 */
class PrefsStorage(
    private val sharedPreferences: SharedPreferences
) {

  fun put(key: String, value: Any?) {
    val edit = sharedPreferences.edit()
    if (value == null) {
      edit.remove(key)
    } else {
      when (value) {
        is Boolean -> edit.putBoolean(key, value)
        is Long -> edit.putLong(key, value)
        is Float -> edit.putFloat(key, value)
        is Int -> edit.putInt(key, value)
        is String -> edit.putString(key, value)
      }
    }
    edit.apply()
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> get(key: String): T? = sharedPreferences.all[key] as T?

  fun getAll(): MutableMap<String, *> = sharedPreferences.all
}