package com.example.marcin.mypodcasts.mvp

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by MARCIN on 2017-10-14.
 */
abstract class BasePresenter<T : MvpView> : MvpPresenter {

    @Inject lateinit var view: T

    protected var disposable: CompositeDisposable? = null

    override fun onViewCreated() {
        //to be used by child class
    }

    override fun start() {
        //to be used by child class
    }

    override fun resume() {
        disposable = CompositeDisposable()
    }

    override fun pause() {
        disposable?.clear()
        disposable = null
    }

    override fun stop() {
        //to be used by child class
    }

    override fun destroy() {
        //to be used by child class
    }
}