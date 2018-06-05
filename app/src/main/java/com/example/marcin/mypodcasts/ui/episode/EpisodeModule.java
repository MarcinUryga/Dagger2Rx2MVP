package com.example.marcin.mypodcasts.ui.episode;

import com.example.marcin.mypodcasts.ui.podcast_details.PodcastIdParam;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by marci on 2018-05-18.
 */
@Module
public abstract class EpisodeModule {

  @Binds
  abstract EpisodeContract.View bindsView(EpisodeActivity view);

  @Binds
  abstract EpisodeContract.Presenter bindsPresenter(EpisodePresenter presenter);

  @Provides
  static PodcastIdParam providePodcastIdParam(EpisodeActivity activity) {
    return new PodcastIdParam(activity.getIntent().getExtras());
  }

  @Provides
  static EpisodeIdParams provideEpisodeIdParams(EpisodeActivity activity) {
    return new EpisodeIdParams(activity.getIntent().getExtras());
  }
}
