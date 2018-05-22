package com.example.marcin.mypodcasts.ui.launch;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2018-05-11.
 */
@Module
public abstract class LaunchModule {

    @Binds
    abstract public LaunchContract.View bindsLanuchView(LaunchActivity activity);

    @Binds
    abstract public LaunchContract.Presenter bindsLaunchPresenter(LaunchPresenter presenter);
}
