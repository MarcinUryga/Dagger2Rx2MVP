package com.example.marcin.mypodcasts.ui.register

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.model.UserResponse
import com.example.marcin.mypodcasts.storage.UserStorage
import com.example.marcin.mypodcasts.mvp.BasePresenter
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
    private val sharedPreferences: SharedPreferences
) : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

  private val userStorage = UserStorage(sharedPreferences)

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
          userStorage.saveToStorage(
              UserResponse(
                  username = registerRequest.username,
                  objectId = response.objectId,
                  firstName = registerRequest.firstName,
                  email = registerRequest.email,
                  lastName = registerRequest.lastName,
                  sessionToken = response.sessionToken
              )
          )
          view.startMainActivity()
        }, { error ->
          Timber.d(error.localizedMessage)
        })
    disposables?.add(disposable)
  }

}