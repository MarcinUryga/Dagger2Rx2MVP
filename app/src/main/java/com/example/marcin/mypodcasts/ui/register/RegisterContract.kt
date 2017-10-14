package com.example.marcin.mypodcasts.ui.register

/**
 * Created by marci on 2017-10-13.
 */
interface RegisterContract {

    interface View {

        fun startLoginActivity()

        fun hideKeyboard()

        fun validateName(): Boolean

        fun validateSurname(): Boolean

        fun validateEmailPattern(): Boolean

        fun validateEmptyEmailField(): Boolean

        fun validatePasswordLength(): Boolean

        fun validateEmptyPasswordField(): Boolean

        fun validateConfirmPasswordLength(): Boolean

        fun validateEmptyConfirmPasswordField(): Boolean

        fun showNameError()

        fun showSurnameError()

        fun showEmailError()

        fun showPasswordError()

        fun showConfirmPasswordError()
    }

    interface Presenter {

        fun onRegisterClicked()
    }
}