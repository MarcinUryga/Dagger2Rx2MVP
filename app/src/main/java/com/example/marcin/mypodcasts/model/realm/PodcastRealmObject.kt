package com.example.marcin.mypodcasts.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-05-11.
 */
open class PodcastRealmObject : RealmObject() {
  @PrimaryKey
  var podcastId: Long = 0
  var numberOfEpisodes: Int = 0
  var title: String = ""
  var description: String = ""
  var fullUrl: String = ""
  var thumbUrl: String = ""
}