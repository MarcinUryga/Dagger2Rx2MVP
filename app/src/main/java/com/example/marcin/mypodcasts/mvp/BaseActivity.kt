package com.example.marcin.mypodcasts.mvp

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.marcin.mypodcasts.R
import javax.inject.Inject

/**
 * Created by MARCIN on 2017-10-14.
 */
abstract class BaseActivity<P : MvpPresenter> : AppCompatActivity() {

    @Inject lateinit var presenter: P
    //fragmentInjector

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    protected fun showErrorDialog(errorMsg: String?) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.error))
                .setMessage(errorMsg)
                .setPositiveButton(getString(R.string.ok), null)
                .show()
    }
}