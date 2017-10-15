package com.example.marcin.mypodcasts.di

import com.example.marcin.mypodcasts.ui.login.LoginActivity
import com.example.marcin.mypodcasts.ui.login.LoginModule
import com.example.marcin.mypodcasts.ui.register.RegisterActivity
import com.example.marcin.mypodcasts.ui.register.RegisterModule
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

    @ScreenScope
    @ContributesAndroidInjector(modules = arrayOf(RegisterModule::class))
    abstract fun registerActivity(): RegisterActivity
}