package com.example.marcin.mypodcasts.ui.main

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.storage.UserSharedPref
import com.example.marcin.mypodcasts.ui.login.LoginActivity
import com.example.marcin.mypodcasts.ui.my_podcasts.MyPodcastsFragment
import com.example.marcin.mypodcasts.ui.search_podcasts.SearchPodcastsFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_activity_layout.*


/**
 * Created by marci on 2017-10-15.
 */
class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity_layout)
    bottomNavigationView.selectedItemId = R.id.my_podcasts
    startNewFragment(MyPodcastsFragment())
    navigateWithBottomView()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.logoutButton) {
      presenter.logoutUser()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  fun navigateWithBottomView() {
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.search_podcasts -> startNewFragment(SearchPodcastsFragment())
        R.id.my_podcasts -> startNewFragment(MyPodcastsFragment())
        else -> throw Exception("Illegal fragment")
      }
      true
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun openLoginActivity(userSharedPref: UserSharedPref) {
    Toast.makeText(baseContext, "${userSharedPref.logged()}  sdad ${userSharedPref.getUserId()}", Toast.LENGTH_SHORT).show()
    startActivity(LoginActivity.newIntent(this))
  }

  private fun startNewFragment(newFragment: Fragment) {
    fragmentManager.beginTransaction()
        .replace(R.id.containerForFragments, newFragment)
        .commit()
  }

  companion object {
    fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
  }
}