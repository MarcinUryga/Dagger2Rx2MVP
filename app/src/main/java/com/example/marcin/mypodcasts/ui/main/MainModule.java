package com.example.marcin.mypodcasts.ui.main;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2017-10-15.
 */

@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.View bindView(MainActivity activity);

    @Binds
    abstract MainContract.Presenter bindPresenter(MainPresenter presenter);
}
