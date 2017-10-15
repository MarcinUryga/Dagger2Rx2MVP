package com.example.marcin.mypodcasts.ui.register;

import com.example.marcin.mypodcasts.api.PodcastApi;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by MARCIN on 2017-10-15.
 */

@Module
public abstract class RegisterModule {

    @Binds
    abstract RegisterContract.View bindView(RegisterActivity activity);

    @Binds
    abstract RegisterContract.Presenter bindPresenter(RegisterPresenter presenter);

    @Provides
    static RegisterManager provideRegisterManager(RegisterActivity registerActivity, PodcastApi podcastApi) {
        return new RegisterManager(podcastApi);
    }

}
