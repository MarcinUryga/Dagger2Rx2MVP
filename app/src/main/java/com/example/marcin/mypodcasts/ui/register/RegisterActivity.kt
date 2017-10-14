package com.example.marcin.mypodcasts.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import com.example.marcin.mypodcasts.R
import com.example.marcin.mypodcasts.common.KeyboardUtils
import com.example.marcin.mypodcasts.ui.login.LoginActivity
import kotlinx.android.synthetic.main.register_layout.*

/**
 * Created by marci on 2017-10-13.
 */
class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var presenter: RegisterContract.Presenter

    private var usernameErrorMessage: String? = null

    private var passwordErrorMessage: String? = null

    private var confirmPasswordErrorMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        presenter = RegisterPresenter(this)
        registerButton.setOnClickListener { presenter.onRegisterClicked() }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun startLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun hideKeyboard() {
        KeyboardUtils.hide(this.currentFocus)
    }

    override fun validateName(): Boolean {
        if (nameEditText.text.isEmpty()) {
            return true
        }
        return false
    }

    override fun validateSurname(): Boolean {
        if (surnameEditText.text.isEmpty()) {
            return true
        }
        return false
    }

    override fun validateEmailPattern(): Boolean {
        emailInputLayout.error = null
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches()) {
            usernameErrorMessage = getString(R.string.this_is_not_an_email)
            return true
        }
        return false
    }

    override fun validateEmptyEmailField(): Boolean {
        emailInputLayout.error = null
        if (emailEditText.text.isEmpty()) {
            usernameErrorMessage = getString(R.string.this_field_cannot_be_empty)
            return true
        }
        return false
    }

    override fun validatePasswordLength(): Boolean {
        passwordInputLayout.error = null
        if (passwordEditText.text.length <= 3) {
            passwordErrorMessage = getString(R.string.password_should_be_longer)
            return true
        }
        return false
    }

    override fun validateEmptyPasswordField(): Boolean {
        passwordInputLayout.error = null
        if (passwordEditText.text.isEmpty()) {
            passwordErrorMessage = getString(R.string.this_field_cannot_be_empty)
            return true
        }
        return false
    }

    override fun validateConfirmPasswordLength(): Boolean {
        confirmPasswordInputLayout.error = null
        if (confirmPasswordEditText.text.length <= 3) {
            confirmPasswordErrorMessage = getString(R.string.password_should_be_longer)
            return true
        }
        return false
    }

    override fun validateEmptyConfirmPasswordField(): Boolean {
        confirmPasswordInputLayout.error = null
        if (confirmPasswordEditText.text.isEmpty()) {
            confirmPasswordErrorMessage = getString(R.string.this_field_cannot_be_empty)
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
}