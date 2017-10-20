package com.example.marcin.mypodcasts.ui.my_podcasts

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import kotlinx.android.synthetic.main.empty_podcasts_view.*
import kotlinx.android.synthetic.main.my_podcasts_fragment.*

/**
 * Created by marci on 2017-10-17.
 */
class MyPodcastsFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.my_podcasts_fragment, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    emptyView.visibility = View.VISIBLE
    noPodcastsText.text = getString(R.string.no_podcasts_to_show_search)
  }
}