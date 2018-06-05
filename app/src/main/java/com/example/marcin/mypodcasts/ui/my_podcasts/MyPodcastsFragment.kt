package com.example.marcin.mypodcasts.ui.my_podcasts

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseFragment
import com.example.marcin.mypodcasts.ui.my_podcasts.adapter.SubscribedPodcastsAdapter
import com.example.marcin.mypodcasts.ui.my_podcasts.viewmodel.Podcast
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastDetailsActivity
import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.empty_podcasts_view.*
import kotlinx.android.synthetic.main.podcasts_fragment.*

/**
 * Created by marci on 2017-10-17.
 */
class MyPodcastsFragment : BaseFragment<MyPodcastsContract.Presenter>(), MyPodcastsContract.View {
  private val subscribedPodcastsAdapter = SubscribedPodcastsAdapter()

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.podcasts_fragment, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recyclerView.layoutManager = GridLayoutManager(context, 2)
    recyclerView.adapter = subscribedPodcastsAdapter
    presenter.handleClickedPodcast(subscribedPodcastsAdapter.getClickedPublishSubject())
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
    if (progressBar != null) {
      progressBar.visibility = View.INVISIBLE
    }
  }

  override fun showSubscribedPodcasts(podcasts: List<Podcast>) {
    subscribedPodcastsAdapter.addPodcasts(podcasts)
  }

  override fun showEmptyView() {
    emptyPodcastsView.visibility = View.VISIBLE
    noPodcastsText.text = getString(R.string.no_podcasts_to_show_search)
  }

  override fun startPodcastDetails(id: Long) {
    startActivity(PodcastDetailsActivity.newIntent(context, PodcastIdParam(id)))
  }
}
