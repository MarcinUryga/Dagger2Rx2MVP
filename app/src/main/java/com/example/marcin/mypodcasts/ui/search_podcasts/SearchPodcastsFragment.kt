package com.example.marcin.mypodcasts.ui.search_podcasts

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseFragment
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastDetailsActivity
import com.example.marcin.mypodcasts.ui.search_podcasts.adapter.PodcastAdapter
import com.example.marcin.mypodcasts.ui.search_podcasts.viewmodel.Podcast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.empty_podcasts_view.*
import kotlinx.android.synthetic.main.podcasts_fragment.*

/**
 * Created by marci on 2017-10-17.
 */
class SearchPodcastsFragment : BaseFragment<SearchPodcastsContract.Presenter>(), SearchPodcastsContract.View {

  private val podcastAdapter: PodcastAdapter = PodcastAdapter()

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.podcasts_fragment, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    noPodcastsText.text = getString(R.string.no_podcasts_to_show_check_internet)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = podcastAdapter
    presenter.handleSubscription(podcastAdapter.getClickedSubscription())
  }

  override fun showPodcasts(podcastsList: List<Podcast>) {
    podcastAdapter.addPodcasts(podcastsList)
    emptyPodcastsView.visibility = View.INVISIBLE
    recyclerView.visibility = View.VISIBLE
  }

  override fun showLoadError(msg: String) {
    showAlertDialog(msg)
    emptyPodcastsView.visibility = View.VISIBLE
    recyclerView.visibility = View.INVISIBLE
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
    if (progressBar != null) {
      progressBar.visibility = View.INVISIBLE
    }
  }

  override fun updatePodcastView(podcast: Podcast) {
    podcastAdapter.update(podcast)
  }

  private fun showAlertDialog(msg: String) {
    AlertDialog.Builder(context)
        .setTitle(getString(R.string.error))
        .setMessage(msg)
        .setPositiveButton(getString(R.string.ok), null)
        .show()
  }
}