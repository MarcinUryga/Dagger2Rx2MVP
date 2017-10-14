package com.example.marcin.mypodcasts.ui.register

/**
 * Created by marci on 2017-10-13.
 */
class RegisterPresenter(
        private val view: RegisterContract.View
) : RegisterContract.Presenter {

    override fun onRegisterClicked() {
        view.hideKeyboard()
        validateRegisterInput()
    }

    private fun validateRegisterInput() {
        if (view.validateName()) {
            view.showNameError()
        }
        if (view.validateSurname()) {
            view.showSurnameError()
        }
        if (view.validateEmailPattern() || view.validateEmptyEmailField()) {
            view.showEmailError()
        }
        if (view.validateEmptyPasswordField() || view.validatePasswordLength()) {
            view.showPasswordError()
        }
        if (view.validateEmptyConfirmPasswordField() || view.validateConfirmPasswordLength()) {
            view.showConfirmPasswordError()
        } else {
            view.startLoginActivity()
        }
    }

}