package com.example.marcin.mypodcasts.repository

import io.realm.RealmObject
import io.realm.RealmQuery
import timber.log.Timber
import kotlin.reflect.KClass

/**
 * Created by marci on 2018-05-11.
 */
open class RealmRepository(
    private val realmManager: RealmManager
) {

  fun copyOrUpdate(realmObjects: List<RealmObject?>) {
    val realm = realmManager.getInstance()
    try {
      realm.beginTransaction()
      realmObjects.filterNotNull().forEach {
        realm.copyToRealmOrUpdate(it)
      }
      realm.commitTransaction()
    } catch (e: IllegalArgumentException) {
      Timber.e(e.message)
    } finally {
      realm.close()
    }
  }

  fun <RO : RealmObject, R> get(realmClass: KClass<RO>, query: RealmQuery<RO>.() -> R): R? {
    val realm = realmManager.getInstance()
    try {
      return realm.where(realmClass.java).query()
    } finally {
      realm.close()
    }
  }

  fun clear() {
    val realm = realmManager.getInstance()
    try {
      realm.deleteAll()
    } finally {
      realm.close()
    }
  }
}