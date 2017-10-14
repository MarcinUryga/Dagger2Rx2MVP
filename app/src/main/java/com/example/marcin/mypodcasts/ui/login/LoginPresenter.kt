package com.example.marcin.mypodcasts.ui.login

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.login.LoginContract.Presenter
import javax.inject.Inject

/**
 * Created by marci on 2017-10-13.
 */

@ScreenScope
class LoginPresenter @Inject constructor(
) : BasePresenter<LoginContract.View>(), Presenter {

    override fun onLoginClicked() {
        view.hideKeyboard()
        validateLoginInput()
    }

    override fun onRegisterClicked() {
        view.startRegisterActivity()
    }

    private fun validateLoginInput() {
        if (view.validateEmailPattern() || view.validateEmptyEmailField()) {
            view.showEmailError()
        }
        if (view.validateEmptyPasswordField() || view.validatePasswordLength()) {
            view.showPasswordError()
        } else {
            view.startMainMenuActivity()
        }
    }
}