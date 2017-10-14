package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.ui.login.LoginActivity
import com.example.marcin.mypodcasts.ui.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by MARCIN on 2017-10-14.
 */

@Module
abstract class InjectorsModule {

    @ScreenScope
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    abstract fun loginActivity(): LoginActivity
}