package com.example.marcin.mypodcasts.ui.register

import com.example.marcin.mypodcasts.mvp.MvpPresenter
import com.example.marcin.mypodcasts.mvp.MvpView

/**
 * Created by marci on 2017-10-13.
 */
interface RegisterContract {

    interface View : MvpView {

        fun startLoginActivity()

        fun hideKeyboard()

        fun validateNameField(): Boolean

        fun validateSurnameField(): Boolean

        fun validateEmailField(): Boolean

        fun validatePasswordField(): Boolean

        fun validateConfirmPasswordField(): Boolean

        fun showNameError()

        fun showSurnameError()

        fun showEmailError()

        fun showPasswordError()

        fun showConfirmPasswordError()
    }

    interface Presenter : MvpPresenter {

        fun onRegisterClicked(name: String, surname: String, email: String, password: String)
    }
}