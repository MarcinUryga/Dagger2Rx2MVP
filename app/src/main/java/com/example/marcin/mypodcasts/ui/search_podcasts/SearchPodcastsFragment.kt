package com.example.marcin.mypodcasts.ui.search_podcasts

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.model.Podcast
import com.example.marcin.mypodcasts.mvp.BaseFragment
import com.example.marcin.mypodcasts.ui.search_podcasts.adapter.PodcastAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.empty_podcasts_view.*
import kotlinx.android.synthetic.main.search_podcasts_fragment.*

/**
 * Created by marci on 2017-10-17.
 */
class SearchPodcastsFragment : BaseFragment<SearchPodcastsContract.Presenter>(), SearchPodcastsContract.View {

  lateinit var podcastAdapter: PodcastAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    AndroidInjection.inject(this)
    return inflater.inflate(R.layout.search_podcasts_fragment, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    noPodcastsText.text = getString(R.string.no_podcasts_to_show_check_internet)
    presenter.loadPodcasts()
  }

  override fun showPodcasts(podcastsList: List<Podcast>) {
    podcastAdapter = PodcastAdapter(podcastsList)
    recyclerView.layoutManager = LinearLayoutManager(activity.baseContext)
    recyclerView.adapter = podcastAdapter
    emptyPodcastsView.visibility = View.INVISIBLE
    recyclerView.visibility = View.VISIBLE
  }

  override fun showLoadError(msg: String) {
    showAlertDialog(msg)
    emptyPodcastsView.visibility = View.VISIBLE
    recyclerView.visibility = View.INVISIBLE
  }

  private fun showAlertDialog(msg: String) {
    AlertDialog.Builder(activity.baseContext)
        .setTitle(getString(R.string.error))
        .setMessage(msg)
        .setPositiveButton(getString(R.string.ok), null)
        .show()
  }
}