package com.example.marcin.mypodcasts.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


/**
 * Created by marci on 2017-10-22.
 */
data class Subscription(
    val userId: String,
    val podcastId: Long,
    @SerializedName("ACL")
    val acl: JsonObject
)