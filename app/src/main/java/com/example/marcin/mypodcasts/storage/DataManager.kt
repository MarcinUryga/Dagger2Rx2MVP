package com.example.marcin.mypodcasts.storage

import android.content.Context
import android.content.res.Resources
import com.example.marcin.mypodcasts.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by marci on 2017-10-21.
 */

@Singleton
class DataManager @Inject constructor(
    @ApplicationContext context: Context,
    private val dbHelper: DbHelper) {

  @Throws(Exception::class)
  fun createUser(user: User): Long? {
    return dbHelper.insertUser(user)
  }

  @Throws(Resources.NotFoundException::class, NullPointerException::class)
  fun getUser(userId: String): User {
    return dbHelper.getUser(userId)
  }

  fun deleteUser(userId: String) {
    dbHelper.deleteUser(userId)
  }
}