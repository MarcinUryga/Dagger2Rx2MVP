package com.example.marcin.mypodcasts.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by marci on 2017-10-15.
 */
class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
  }

  override fun setTestText(text: String) {
    testText.text = text
  }

  companion object {
    fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
  }
}