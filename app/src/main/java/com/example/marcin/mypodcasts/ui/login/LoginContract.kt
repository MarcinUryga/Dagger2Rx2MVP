package com.example.marcin.mypodcasts.ui.login

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView

/**
 * Created by marci on 2017-10-13.
 */
interface LoginContract {

    interface View : MvpView {

        fun startMainMenuActivity()

        fun startRegisterActivity()

        fun hideKeyboard()

        fun validateEmailField(): Boolean

        fun validatePasswordField(): Boolean

        fun showEmailError()

        fun showPasswordError()

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(msg: String)
    }

    interface Presenter : MvpPresenter {

        fun onLoginClicked(email: String, password: String)

        fun onRegisterClicked()
    }
}