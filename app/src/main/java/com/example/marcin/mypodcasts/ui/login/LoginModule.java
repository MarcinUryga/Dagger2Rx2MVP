package com.example.marcin.mypodcasts.ui.login;

import com.example.marcin.mypodcasts.api.PodcastApi;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by MARCIN on 2017-10-14.
 */

@Module
public abstract class LoginModule {

    @Binds
    public abstract LoginContract.View bindView(LoginActivity view);

    @Binds
    public abstract LoginContract.Presenter bindPresenter(LoginPresenter presenter);
//
//    @Provides
//    public static UserLoginUseCase provideUserLoginUseCase(LoginActivity loginActivity, PodcastApi podcastApi) {
//        return new UserLoginUseCase(podcastApi);
//    }
}
