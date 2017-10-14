package com.example.marcin.mypodcasts.ui.login;

import com.example.marcin.mypodcasts.ui.register.RegisterPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * Created by MARCIN on 2017-10-14.
 */

@Module
public abstract class LoginModule {

    @Binds
    public abstract LoginContract.View bindView(LoginActivity view);

    @Binds
    public abstract LoginContract.Presenter bindPresenter(LoginPresenter presenter);
}
