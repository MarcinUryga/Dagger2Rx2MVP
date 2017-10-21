package com.example.marcin.mypodcasts.di

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by MARCIN on 2017-10-14.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseInfo
