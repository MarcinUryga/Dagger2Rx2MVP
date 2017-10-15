package com.example.marcin.mypodcasts.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.common.KeyboardUtils
import com.example.marcin.mypodcasts.mvp.BaseActivity
import com.example.marcin.mypodcasts.ui.login.LoginActivity
import com.example.marcin.mypodcasts.ui.main.MainActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.register_layout.*

/**
 * Created by marci on 2017-10-13.
 */
class RegisterActivity : BaseActivity<RegisterContract.Presenter>(), RegisterContract.View {

  private var usernameErrorMessage: String? = null

  private var passwordErrorMessage: String? = null

  private var confirmPasswordErrorMessage: String? = null

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, RegisterActivity::class.java)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.register_layout)
    registerButton.setOnClickListener {
      presenter.onRegisterClicked(
          nameEditText.text.toString(),
          surnameEditText.text.toString(),
          emailEditText.text.toString(),
          passwordEditText.text.toString()
      )
    }
  }

  override fun startMainActivity() {
    startActivity(MainActivity.newIntent(this))
  }

  override fun hideKeyboard() {
    KeyboardUtils.hide(this.currentFocus)
  }

  override fun validateNameField(): Boolean {
    nameInputLayout.error = null
    if (nameEditText.text.isEmpty()) {
      return true
    }
    return false
  }

  override fun validateSurnameField(): Boolean {
    surnameInputLayout.error = null
    if (surnameEditText.text.isEmpty()) {
      return true
    }
    return false
  }

  override fun validateEmailField(): Boolean {
    emailInputLayout.error = null
    if (emailEditText.text.isEmpty()) {
      usernameErrorMessage = getString(R.string.this_field_cannot_be_empty)
      return true
    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches()) {
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

  override fun validateConfirmPasswordField(): Boolean {
    confirmPasswordInputLayout.error = null
    if (confirmPasswordEditText.text.isEmpty()) {
      confirmPasswordErrorMessage = getString(R.string.this_field_cannot_be_empty)
      return true
    } else if (confirmPasswordEditText.text.length <= 3) {
      confirmPasswordErrorMessage = getString(R.string.password_should_be_longer)
      return true
    } else if (confirmPasswordEditText.text.toString() != passwordEditText.text.toString()) {
      confirmPasswordErrorMessage = getString(R.string.wrong_password)
      return true
    }
    return false
  }

  override fun showEmailError() {
    emailInputLayout.error = usernameErrorMessage
  }

  override fun showPasswordError() {
    passwordInputLayout.error = passwordErrorMessage
  }

  override fun showNameError() {
    nameInputLayout.error = getString(R.string.this_field_cannot_be_empty)
  }

  override fun showSurnameError() {
    surnameInputLayout.error = getString(R.string.this_field_cannot_be_empty)
  }

  override fun showConfirmPasswordError() {
    confirmPasswordInputLayout.error = confirmPasswordErrorMessage
  }

  override fun showProgressBar() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgressBar() {
    progressBar.visibility = View.INVISIBLE
  }
}