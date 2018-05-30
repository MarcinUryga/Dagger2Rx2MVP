package com.example.marcin.mypodcasts.ui.launch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.services.prefetch_data.PrefetchService
import com.example.marcin.mypodcasts.ui.main.MainActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.launch_activity.*


/**
 * Created by marci on 2018-05-11.
 */
class LaunchActivity : BaseActivity<LaunchContract.Presenter>(), LaunchContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.launch_activity)
  }

  override fun startMainActivity() {
    startActivity(MainActivity.newIntent(this))
    finish()
  }

  override fun displayLaunchAnimation(launchScreenDuration: Long) {
    val rotate = RotateAnimation(0f, 1440f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    rotate.duration = 2000
    rotate.interpolator = LinearInterpolator() as Interpolator?
    appIcon.startAnimation(rotate)
  }

  override fun startPrefetchService() {
    startService(PrefetchService.newIntent(this))
  }

  companion object {
    fun newIntent(context: Context) = Intent(context, LaunchActivity::class.java)
  }
}