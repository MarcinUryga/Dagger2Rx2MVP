package com.example.marcin.mypodcasts.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.common.KeyboardUtils
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.ui.main.MainActivity
import com.example.marcin.mypodcasts.ui.register.RegisterActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.login_layout.*

/**
 * Created by marci on 2017-10-12.
 */
class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

  private var usernameErrorMessage: String? = null

  private var passwordErrorMessage: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.login_layout)
    loginButton.setOnClickListener {
      presenter.onLoginClicked("${usernameEditText.text}", "${passwordEditText.text}")
    }
    registerButton.setOnClickListener { presenter.onRegisterClicked() }
  }

  override fun startMainMenuActivity() {
    startActivity(MainActivity.newIntent(this))
  }

  override fun startRegisterActivity() {
    startActivity(RegisterActivity.newIntent(this))
  }

  override fun hideKeyboard() {
    KeyboardUtils.hide(this.currentFocus)
  }


  override fun validateEmailField(): Boolean {
    usernameInputLayout.error = null
    if (usernameEditText.text.isEmpty()) {
      usernameErrorMessage = getString(R.string.this_field_cannot_be_empty)
      return true
    } else if (!Patterns.EMAIL_ADDRESS.matcher(usernameEditText.text).matches()) {
      usernameErrorMessage = getString(R.string.this_is_not_an_email)
      return true
    }
    return false
  }

  override fun validatePasswordField(): Boolean {
    passwordInputLayout.error = null
    if (passwordEditText.text.isEmpty()) {
      passwordErrorMessage = getString(R.string.this_field_cannot_be_empty)
      return true
    } else if (passwordEditText.text.length <= 3) {
      passwordErrorMessage = getString(R.string.password_should_be_longer)
      return true
    }
    return false
  }

  override fun showEmailError() {
    usernameInputLayout.error = usernameErrorMessage
  }

  override fun showPasswordError() {
    passwordInputLayout.error = passwordErrorMessage
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
  }

  override fun showError(msg: String) {
    showErrorDialog(msg)
  }

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, LoginActivity::class.java)
    }
  }
}
