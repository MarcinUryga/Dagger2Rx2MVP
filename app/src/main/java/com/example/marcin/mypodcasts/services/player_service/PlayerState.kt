package com.example.marcin.mypodcasts.services.player_service

/**
 ** Created by marci on 2018-06-04.
 */
enum class PlayerState(stateName: String) {
  LOADING("loading"),
  PLAY("play"),
  PAUSE("pause")
}