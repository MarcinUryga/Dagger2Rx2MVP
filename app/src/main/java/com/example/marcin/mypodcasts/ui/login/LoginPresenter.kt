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
        if (view.validateEmailField()) {
            view.showEmailError()
        }
        if (view.validatePasswordField()) {
            view.showPasswordError()
        } else {
            view.startMainMenuActivity()
        }
    }
}