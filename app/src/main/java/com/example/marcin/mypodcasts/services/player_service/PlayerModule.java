package com.example.marcin.mypodcasts.services.player_service;

import dagger.Binds;
import dagger.Module;

/**
 * Created by marci on 2018-05-30.
 */

@Module
public abstract class PlayerModule {

    @Binds
    abstract public PlayerContract.Service bindsService(PlayerService service);

    @Binds
    abstract public PlayerContract.Presenter bindsPresenter(PlayerPresenter presenter);
}
