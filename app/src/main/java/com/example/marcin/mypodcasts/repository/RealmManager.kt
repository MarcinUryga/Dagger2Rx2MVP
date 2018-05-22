package com.example.marcin.mypodcasts.repository

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by marci on 2018-05-11.
 */
object RealmManager {

  fun init(context: Context) {
    Realm.init(context)
    Realm.setDefaultConfiguration(
        RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
    )
  }

  fun getInstance(): Realm = Realm.getDefaultInstance()
}