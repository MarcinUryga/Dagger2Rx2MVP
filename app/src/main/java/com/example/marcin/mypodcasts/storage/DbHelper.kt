package com.example.marcin.mypodcasts.storage

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.marcin.mypodcasts.di.ApplicationContext
import com.example.marcin.mypodcasts.di.DatabaseInfo
import com.example.marcin.mypodcasts.model.Podcast
import java.sql.SQLException
import javax.inject.Singleton


/**
 * Created by marci on 2017-10-20.
 */

@Singleton
class DbHelper(
    @ApplicationContext context: Context,
    @DatabaseInfo dbName: String,
    @DatabaseInfo version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {
  override fun onCreate(db: SQLiteDatabase) {
    createTable(db)
  }

  override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    db.execSQL("DROP TABLE IF EXISTS " + PODCAST_TABLE_NAME)
    onCreate(db)
  }

  private fun createTable(db: SQLiteDatabase) {
    try {
      db.execSQL(
          "CREATE TABLE IF NOT EXISTS "
              + PODCAST_TABLE_NAME + "("
              + PODCAST_COLUMN_PODCAST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
              + PODCAST_COLUMN_PODCAST_TITLE + " VARCHAR(20), "
              + PODCAST_COLUMN_PODCAST_DESCRIPTION + " VARCHAR(100),"
              + PODCAST_COLUMN_NUMBER_OF_EPISODES + " INTEGER,"
              + PODCAST_COLUMN_PODCAST_FULL_URL + " VARCHAR(100),"
              + PODCAST_COLUMN_PODCAST_THUMB_URL + " VARCHAR(100)" +
              ")"
      )
    } catch (e: SQLException) {
      e.printStackTrace()
    }
  }

  @Throws(Resources.NotFoundException::class, NullPointerException::class)
  protected fun getPodcast(podcastId: Long): Podcast {
    var cursor: Cursor? = null
    try {
      val db = this.readableDatabase
      cursor = db.rawQuery(
          "SELECT * FROM "
              + PODCAST_TABLE_NAME
              + " WHERE "
              + PODCAST_COLUMN_PODCAST_ID
              + " = ? ",
          arrayOf((podcastId).toString() + ""))

      if (cursor!!.getCount() > 0) {
        cursor.moveToFirst()
        return Podcast(
            podcastId = cursor.getLong(cursor.getColumnIndex(PODCAST_COLUMN_PODCAST_ID)),
            numberOfEpisodes = cursor.getInt(cursor.getColumnIndex(PODCAST_COLUMN_NUMBER_OF_EPISODES)),
            title = cursor.getString(cursor.getColumnIndex(PODCAST_COLUMN_PODCAST_TITLE)),
            description = cursor.getString(cursor.getColumnIndex(PODCAST_COLUMN_PODCAST_DESCRIPTION)),
            fullUrl = cursor.getString(cursor.getColumnIndex(PODCAST_COLUMN_PODCAST_FULL_URL)),
            thumbUrl = cursor.getString(cursor.getColumnIndex(PODCAST_COLUMN_PODCAST_THUMB_URL))
        )

      } else {
        throw Resources.NotFoundException("User with id $podcastId does not exists")
      }
    } catch (e: NullPointerException) {
      e.printStackTrace()
      throw e
    } finally {
      if (cursor != null)
        cursor.close()
    }
  }

  @Throws(Exception::class)
  protected fun insertPodcast(podcast: Podcast): Long? {
    try {
      val db = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(PODCAST_COLUMN_PODCAST_TITLE, podcast.title)
      contentValues.put(PODCAST_COLUMN_NUMBER_OF_EPISODES, podcast.numberOfEpisodes)
      contentValues.put(PODCAST_COLUMN_PODCAST_DESCRIPTION, podcast.description)
      contentValues.put(PODCAST_COLUMN_PODCAST_FULL_URL, podcast.fullUrl)
      contentValues.put(PODCAST_COLUMN_PODCAST_THUMB_URL, podcast.thumbUrl)
      return db.insert(PODCAST_TABLE_NAME, null, contentValues)
    } catch (e: Exception) {
      e.printStackTrace()
      throw e
    }
  }

  companion object {
    const val PODCAST_TABLE_NAME = "podcasts"
    const val PODCAST_COLUMN_PODCAST_ID = "id"
    const val PODCAST_COLUMN_NUMBER_OF_EPISODES = "number_of_episodes"
    const val PODCAST_COLUMN_PODCAST_TITLE = "podcast_title"
    const val PODCAST_COLUMN_PODCAST_DESCRIPTION = "podcast_description"
    const val PODCAST_COLUMN_PODCAST_FULL_URL = "podcast_full_url"
    const val PODCAST_COLUMN_PODCAST_THUMB_URL = "podcast_thumb_url"
  }
}