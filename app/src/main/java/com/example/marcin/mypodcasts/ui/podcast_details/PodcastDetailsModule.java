package com.example.marcin.mypodcasts.ui.podcast_details;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by marci on 2018-05-14.
 */
@Module
public abstract class PodcastDetailsModule {

    @Binds
    abstract PodcastDetailsContract.View bindPodcastDetailsView(PodcastDetailsActivity view);

    @Binds
    abstract PodcastDetailsContract.Presenter bindPodcastDEtailsPresenter(PodcastDetailsPresenter presenter);

    @Provides
    static PodcastIdParam providesPodcastIdParam(PodcastDetailsActivity activity) {
        return new PodcastIdParam(activity.getIntent().getExtras());
    }
}
