package com.example.marcin.mypodcasts.ui.podcast_details

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

/**
 * Created by marci on 2017-10-20.
 */
class PodcastDetailsActivity : AppCompatActivity() {


  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, PodcastDetailsActivity::class.java)
    }
  }
}