package com.example.marcin.mypodcasts.storage

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.marcin.mypodcasts.di.ApplicationContext
import com.example.marcin.mypodcasts.di.DatabaseInfo
import java.sql.SQLException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by marci on 2017-10-20.
 */

@Singleton
class DbHelper @Inject constructor(
    @ApplicationContext context: Context,
    @DatabaseInfo dbName: String,
    @DatabaseInfo version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {
  override fun onCreate(db: SQLiteDatabase) {
    createTable(db)
  }

  override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
    onCreate(db)
  }

  private fun createTable(db: SQLiteDatabase) {
    try {
      db.execSQL(
          "CREATE TABLE IF NOT EXISTS "
              + TABLE_NAME + "("
              + USER_ID + " VARCHAR(20), "
              + SURNAME + " VARCHAR(20), "
              + EMAIL + " VARCHAR(50),"
              + NAME + " VARCHAR(50),"
              + USERNAME + " VARCHAR(50),"
              + SESSION_TOKEN + " VARCHAR(50)" +
              ")"
      )
    } catch (e: SQLException) {
      e.printStackTrace()
    }
  }

  @Throws(Exception::class)
  fun insertUser(user: User): Long? {
    try {
      val db = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(USER_ID, user.id)
      contentValues.put(SESSION_TOKEN, user.sessionToken)
      contentValues.put(NAME, user.firstName)
      contentValues.put(SURNAME, user.lastName)
      contentValues.put(USERNAME, user.username)
      contentValues.put(EMAIL, user.email)
      return db.insert(TABLE_NAME, null, contentValues)
    } catch (e: Exception) {
      e.printStackTrace()
      throw e
    }
  }

  @Throws(Resources.NotFoundException::class, NullPointerException::class)
  fun getUser(objecId: String): User {
    var cursor: Cursor? = null
    try {
      val db = this.readableDatabase
      cursor = db.rawQuery(
          "SELECT * FROM "
              + TABLE_NAME
              + " WHERE "
              + USER_ID
              + " = ? ",
          arrayOf((objecId) + ""))

      if (cursor!!.getCount() > 0) {
        cursor.moveToFirst()
        return User(
            id = cursor.getString(cursor.getColumnIndex(USER_ID)),
            firstName = cursor.getString(cursor.getColumnIndex(NAME)),
            lastName = cursor.getString(cursor.getColumnIndex(SURNAME)),
            username = cursor.getString(cursor.getColumnIndex(USERNAME)),
            sessionToken = cursor.getString(cursor.getColumnIndex(SESSION_TOKEN)),
            email = cursor.getString(cursor.getColumnIndex(EMAIL))
        )

      } else {
        throw Resources.NotFoundException("User with id $objecId does not exists")
      }
    } catch (e: NullPointerException) {
      e.printStackTrace()
      throw e
    } finally {
      if (cursor != null)
        cursor.close()
    }
  }

  fun deleteUser(objecId: String) {
    this.writableDatabase
        .delete(
            TABLE_NAME,
            "$USER_ID='$objecId'",
            null
        )
  }

  companion object {
    const val TABLE_NAME = "user"
    const val USER_ID = "id"
    const val SESSION_TOKEN = "session_token"
    const val NAME = "name"
    const val SURNAME = "surname"
    const val USERNAME = "username"
    const val EMAIL = "podcast_description"
  }
}