package com.example.marcin.mypodcasts.services.prefetch_data;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2018-05-14.
 */
@Module
public abstract class PrefetchModule {

    @Binds
    abstract PrefetchContract.Service bindPrefetchService(PrefetchService service);

    @Binds
    abstract PrefetchContract.Presenter bindPrefetchPresenter(PrefetchPresenter presenter);
}
