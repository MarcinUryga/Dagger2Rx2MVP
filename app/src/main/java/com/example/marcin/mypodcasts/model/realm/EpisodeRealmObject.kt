package com.example.marcin.mypodcasts.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by marci on 2018-05-14.
 */
open class EpisodeRealmObject : RealmObject() {
  @PrimaryKey
  var episodeId: Long = 0
  var title: String = ""
  var audioUrl: String = ""
  var description: String = ""
  var duration: String = ""
  var updatedAt: String = ""
  var createdAt: String = ""
  var podcastId: Long = 0
}