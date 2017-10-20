package com.example.marcin.mypodcasts.ui.search_podcasts;

import com.example.marcin.mypodcasts.ui.main.MainContract;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2017-10-17.
 */

@Module
public abstract class SearchPodcastsModule {

    @Binds
    abstract SearchPodcastsContract.View bindView(SearchPodcastsFragment activity);

    @Binds
    abstract SearchPodcastsContract.Presenter bindPresenter(SearchPodcastsPresenter presenter);
}
