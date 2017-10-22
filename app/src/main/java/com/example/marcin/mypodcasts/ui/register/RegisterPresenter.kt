package com.example.marcin.mypodcasts.ui.register

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.storage.DataManager
import com.example.marcin.mypodcasts.storage.User
import com.example.marcin.mypodcasts.storage.UserSharedPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2017-10-13.
 */

@ScreenScope
class RegisterPresenter @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val sharedPreferences: SharedPreferences,
    private val dataManager: DataManager
) : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

  private val userSharedPref = UserSharedPref(sharedPreferences)

  override fun onRegisterClicked(
      name: String,
      surname: String,
      email: String,
      password: String
  ) {
    view.hideKeyboard()

    validateRegisterInput(RegisterRequest(
        username = email,
        firstName = name,
        lastName = surname,
        email = email,
        password = password
    ))
  }

  private fun validateRegisterInput(registerRequest: RegisterRequest) {
    if (view.validateNameField()) {
      view.showNameError()
    }
    if (view.validateSurnameField()) {
      view.showSurnameError()
    }
    if (view.validateEmailField()) {
      view.showEmailError()
    }
    if (view.validatePasswordField()) {
      view.showPasswordError()
    }
    if (view.validateConfirmPasswordField()) {
      view.showConfirmPasswordError()
    } else {
      registerToApplication(registerRequest)
    }
  }

  private fun registerToApplication(registerRequest: RegisterRequest) {
    val disposable = userRegisterUseCase.tryToRegister(registerRequest)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe({ response ->
          storageData(response, registerRequest)
          view.startMainActivity()
        }, { error ->
          Timber.d(error.localizedMessage)
        })
    disposables?.add(disposable)
  }

  private fun storageData(response: UserResponse, request: RegisterRequest) {
    dataManager.createUser(
        User(
            id = response.objectId,
            sessionToken = response.sessionToken,
            firstName = request.firstName,
            lastName = request.lastName,
            username = request.username,
            email = request.email
        ))
    userSharedPref.saveSessionToken(response.objectId)
  }
}