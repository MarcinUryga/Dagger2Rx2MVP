package com.example.marcin.mypodcasts.ui.my_podcasts;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2018-05-11.
 */
@Module
public abstract class MyPodcastsModule {

    @Binds
    abstract public MyPodcastsContract.View bindsMyPodcastsView(MyPodcastsFragment view);

    @Binds
    abstract public MyPodcastsContract.Presenter bindsMyPodcastsPresenter(MyPodcastsPresenter presenter);
}
