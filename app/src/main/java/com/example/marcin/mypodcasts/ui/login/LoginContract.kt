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

        fun validateEmailPattern(): Boolean

        fun validateEmptyEmailField(): Boolean

        fun validatePasswordLength(): Boolean

        fun validateEmptyPasswordField(): Boolean

        fun showEmailError()

        fun showPasswordError()
    }

    interface Presenter : MvpPresenter {

        fun onLoginClicked()

        fun onRegisterClicked()
    }
}